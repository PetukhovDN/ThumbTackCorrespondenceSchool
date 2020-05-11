package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ProposalDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.enums.ElectionsStatus;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.CandidateProgram;
import net.thumbtack.school.elections.model.Proposal;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * DataAccessObject для работы с предложениями избирателей.
 * Методы:
 * выдвижение (создание) предложения,
 * добавление/изменение рейтинга к предложению (оценка предложения),
 * удаление рейтинга у предложения (удаление оценки),
 * получение списка всех предложений с их средней оценкой (отсортированные по оценке). Пока без сортировки.
 */
public class ProposalDaoImpl implements ProposalDao {
    private final Database database;

    public ProposalDaoImpl() {
        database = Database.getInstance();
    }

    /**
     * Избиратель осуществивший запрос выдвигает предложение для программы кандидатов в мэры.
     *
     * @param proposal Переданное предложение избирателя для добавления.
     * @param token    Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором.
     */
    @Override
    public UUID makeProposal(Proposal proposal, UUID token) throws ElectionsException {
        if (database.getElectionsStatus().equals(ElectionsStatus.ELECTIONS_STARTED)) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        database.getProposalMap().putIfAbsent(proposal.getProposalInfo(), proposal);
        if (!database.getProposalsFromCandidateMap().containsKey(database.getVotersMap().get(token).getFullName())) {
            CandidateProgram candidateProgram = new CandidateProgram();
            candidateProgram.getProposals().add(proposal);
            database.getProposalsFromCandidateMap().put(database.getVotersMap().get(token).getFullName(), candidateProgram);
        }
        database.getProposalsFromCandidateMap().get(database.getVotersMap().get(token).getFullName()).getProposals().add(proposal);
        return token;
    }

    /**
     * Избиратель осуществивший запрос оценивает переданное в запросе предложение для кандидатскоц программы по шкале от 1 до 5.
     *
     * @param proposal Переданное предложение избирателя для добавления/изменения рейтинга (оценки).
     * @param rate     Рейтинг которым избиратель оценил предложение.
     * @param token    Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором,
     *                            в случае если такого предложения не существовало,
     *                            в случае если автор пытается изменить оценку собственного предложения (что запрещено по условиям).
     */
    @Override
    public UUID addRatingForProposal(String proposal, int rate, UUID token) throws ElectionsException {
        if (database.getElectionsStatus().equals(ElectionsStatus.ELECTIONS_STARTED)) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        if (!database.getProposalMap().containsKey(proposal)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_INFO);
        }
        if (database.getProposalMap().get(proposal).getAuthorToken().equals(token)) {
            throw new ElectionsException(ExceptionErrorCode.SAME_PROPOSAL_AUTHOR);
        }
        database.getProposalMap().get(proposal).getRating().put(token, rate);
        return token;
    }

    /**
     * Избиратель осуществивший запрос меняет или удаляет оценку у переданного в запросе предложения для кандидатской программы.
     *
     * @param proposal Переданное предложение избирателя для удаления рейтинга (оценки).
     * @param token    Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором,
     *                            в случае если такого предложения не существовало.
     *                            в случае если автор пытается изменить оценку собственного предложения (что запрещено по условиям).
     */
    @Override
    public UUID removeRatingFromProposal(String proposal, UUID token) throws ElectionsException {
        if (database.getElectionsStatus().equals(ElectionsStatus.ELECTIONS_STARTED)) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        if (!database.getProposalMap().containsKey(proposal)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_INFO);
        }
        if (database.getProposalMap().get(proposal).getAuthorToken().equals(token)) {
            throw new ElectionsException(ExceptionErrorCode.SAME_PROPOSAL_AUTHOR);
        }
        database.getProposalMap().get(proposal).getRating().remove(token);
        return token;
    }

    /**
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает список всех предложений с их средним рейтингом (отсортированных по рейтингу)
     * в формате map: ключ - предложение, значение - средняя оценка.
     * @throws ElectionsException выбрасывает исключение при попытке осуществления запроса от пользователя с невалидным идентификатором.
     */
    @Override
    public Map<String, Double> getAllProposalsWithRate(UUID token) throws ElectionsException {
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        Map<String, Double> results = new HashMap<>();
        for (Proposal proposal : database.getProposalMap().values()) {
            double sumRatings = 0.0;
            int count = 0;
            for (Integer rating : proposal.getRating().values()) {
                sumRatings += rating;
                count++;
            }
            results.put(proposal.getProposalInfo(), sumRatings / count); //в map добавляется запись с ключом Предложением и значением Средней оценкой
        }

        return results;

//        return results.entrySet().stream()
//                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * @param token          Идентификатор избирателя осуществляющего запрос.
     * @param voterFullNames массив полных имён избирателей, чьи предложения необходимо получить.
     * @return возвращает map в формате: ключ - имя избирателя чьи предложения необходимо было получить, значение - список предложений (его кандидатская программа).
     * @throws ElectionsException выбрасывает исключение при попытке осуществления запроса от пользователя с невалидным идентификатором,
     *                            если у избирателя не было ни одного предложения.
     */
    @Override
    public Map<String, CandidateProgram> getAllProposalsFromVoter(UUID token, String[] voterFullNames) throws ElectionsException {
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        Map<String, CandidateProgram> results = new HashMap<>();
        for (String voterFullName : voterFullNames) {
            if (!database.getProposalsFromCandidateMap().containsKey(voterFullName)) {
                throw new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_PROGRAM);
            }
            results.put(voterFullName, database.getProposalsFromCandidateMap().get(voterFullName));
        }
        return results;
    }
}


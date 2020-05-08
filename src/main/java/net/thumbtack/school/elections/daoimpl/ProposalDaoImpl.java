package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ProposalDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
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
     * @param proposal Переданное предложение избирателя для добавления.
     * @param token    Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором.
     */
    @Override
    public UUID makeProposal(Proposal proposal, UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getVotersMap().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        database.getProposalSet().add(proposal);
        return token;
    }

    /**
     * @param proposal Переданное предложение избирателя для добавления/изменения рейтинга (оценки).
     * @param rate     Рейтинг которым избиратель оценил предложение.
     * @param token    Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором,
     *                            в случае если автор пытается изменить оценку собственного предложения (что запрещено по условиям),
     *                            в случае если такого предложения не существовало.
     */
    @Override
    public UUID addRatingForProposal(String proposal, int rate, UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getVotersMap().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Proposal proposalFromBase : database.getProposalSet()) {
            if (proposalFromBase.getProposalInfo().equals(proposal)) {
                if (proposalFromBase.getAuthorToken().equals(token)) {
                    throw new ElectionsException(ExceptionErrorCode.SAME_PROPOSAL_AUTHOR);
                } else {
                    proposalFromBase.getRating().put(token, rate);
                    return token;
                }
            }
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_INFO);
    }

    /**
     * @param proposal Переданное предложение избирателя для удаления рейтинга (оценки).
     * @param token    Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором,
     *                            в случае если автор пытается изменить оценку собственного предложения (что запрещено по условиям),
     *                            в случае если такого предложения не существовало.
     */
    @Override
    public UUID removeRatingFromProposal(String proposal, UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getVotersMap().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Proposal proposalFromBase : database.getProposalSet()) {
            if (proposalFromBase.getProposalInfo().equals(proposal)) {
                if (proposalFromBase.getAuthorToken().equals(token)) {
                    throw new ElectionsException(ExceptionErrorCode.SAME_PROPOSAL_AUTHOR);
                } else {
                    proposalFromBase.getRating().remove(token);
                    return token;
                }
            }
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_INFO);
    }

    /**
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает список всех предложений с их средним рейтингом (отсортированных по рейтингу)
     * в формате map: ключ - предложение, значение - средняя оценка.
     * @throws ElectionsException выбрасывает исключение при попытке осуществления запроса от пользователя с невалидным идентификатором.
     */
    @Override
    public Map<String, Double> getAllProposals(UUID token) throws ElectionsException {
        Map<String, Double> results = new HashMap<>();
        if (!database.getVotersMap().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Proposal proposal : database.getProposalSet()) {
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
}


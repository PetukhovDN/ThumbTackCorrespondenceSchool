package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.CandidateProgram;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Voter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * DataAccessObject для работы с кандидатами в мэры.
 * Методы:
 * выдвижение кандидатуры,
 * согласие стать кандидатом в мэры,
 * получение списка всех согласившихся кандидатов.
 */
public class CandidateDaoImpl implements CandidateDao {
    private final Database database;

    public CandidateDaoImpl() {
        database = Database.getInstance();
    }

    /**
     * Выдвигает избирателя с переданными именем и фамилией в кандидаты в мэры.
     * Если избиратель выдвигает сам себя - автоматически даёт на это согласие.
     * Сейчас сделано так что избиратель может выдвинуть только одного кандидата.
     *
     * @param candidate Кандидат в мэры (данные: имя и фамилия)
     * @param token     Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором,
     *                            в случае ненахождения кандидата в базе.
     */
    @Override
    public UUID addCandidateToDatabase(Candidate candidate, UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }

        for (Voter voter : database.getVotersMap().values()) {
            if (voter.getFirstName().equals(candidate.getFirstName())
                    && voter.getLastName().equals(candidate.getLastName())) {
                database.getCandidateMap().putIfAbsent(token, candidate);
                if (voter.getToken().equals(token)) {
                    database.getCandidateMap().get(token).setAgreement(true);
                }
                return token;
            }
        }
        throw new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_LIST);
    }

    /**
     * Избиратель совершивший запрос соглашается стать кандидатом в мэры.
     * Если у него есть предложения (т.е. является автором) - то они добавляются в его кандидатскую программу.
     *
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором,
     *                            в случае ненахождения кандидата в базе.
     */
    @Override
    public UUID agreeToBeCandidate(UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        if (!database.getCandidateMap().containsKey(token)) {
            throw new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_LIST);
        }
        database.getCandidateMap().get(token).setAgreement(true);
        for (Proposal proposal : database.getProposalMap().values()) {
            if (proposal.getAuthorToken().equals(token)) {
                database.getCandidateMap().get(token).getCandidateProgram().getProposals().add(proposal);
            }
        }

        return token;
    }

    /**
     * Кандидат добавляет в свою программу предложение (из существующих).
     *
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором,
     *                            в случае ненахождения кандидата в базе,
     *                            в случае если такое предложение еще не было сделано.
     */
    @Override
    public UUID addProposalToCandidateProgram(String proposal, UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        if (!database.getCandidateMap().containsKey(token)) {
            throw new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_LIST);
        }
        if (!database.getProposalMap().containsKey(proposal)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_INFO);
        }
        database.getCandidateMap().get(token).getCandidateProgram().getProposals().add(database.getProposalMap().get(proposal));
        return token;
    }

    /**
     * Кандидат удаляет из своей программы предложение (если оно было).
     *
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором,
     *                            в случае ненахождения кандидата в базе,
     *                            в случае если такое предложение еще не было сделано,
     *                            в случае если у этого кандидата нет такого предложения,
     *                            в случае если является автором предложения которое хочет удалить.
     */
    @Override
    public UUID removeProposalFromCandidateProgram(String proposal, UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        if (!database.getCandidateMap().containsKey(token)) {
            throw new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_LIST);
        }
        if (!database.getProposalMap().containsKey(proposal)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_INFO);
        }
        if (!database.getCandidateMap().get(token).getCandidateProgram().getProposals().contains(database.getProposalMap().get(proposal))) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_CANDIDATE_PROPOSAL);
        }
        if (database.getProposalMap().get(proposal).getAuthorToken().equals(token)) {
            throw new ElectionsException(ExceptionErrorCode.SAME_PROPOSAL_AUTHOR);
        }
        database.getCandidateMap().get(token).getCandidateProgram().getProposals().remove(database.getProposalMap().get(proposal));

        return token;
    }

    /**
     * Формирует список всех кандидатов, согласившихся стать кандидатами в мэры, с указанием программы для каждого из них.
     *
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает список кандидатов.
     * @throws ElectionsException при попытке осуществления запроса от пользователя с невалидным идентификатором.
     */
    @Override
    public Map<Candidate, CandidateProgram> getAllAgreedCandidates(UUID token) throws ElectionsException {
        Map<Candidate, CandidateProgram> resultMap = new HashMap<>();
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Candidate candidate : database.getCandidateMap().values()) {
            if (candidate.isAgreement()) {
                resultMap.put(candidate, candidate.getCandidateProgram());
            }
        }
        return resultMap;
    }
}

package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.Voter;

import java.util.ArrayList;
import java.util.List;
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
        return token;
    }

    /**
     * Формирует списов всех кандидатов, согласившихся стать кандидатами в мэры.
     *
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает список кандидатов.
     * @throws ElectionsException при попытке осуществления запроса от пользователя с невалидным идентификатором.
     */
    @Override
    public List<Candidate> getAllAgreedCandidates(UUID token) throws ElectionsException {
        List<Candidate> resultList = new ArrayList<>();
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Candidate candidate : database.getCandidateMap().values()) {
            if (candidate.isAgreement()) {
                resultList.add(candidate);
            }
        }
        return resultList;
    }
}

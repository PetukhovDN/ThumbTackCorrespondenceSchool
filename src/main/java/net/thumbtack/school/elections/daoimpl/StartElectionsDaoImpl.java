package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.StartElectionsDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.enums.ElectionsStatus;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Candidate;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * DataAccessObject для работы с данными в случае начала голосования.
 * Методы:
 * начать голосование,
 * проголосовать за кандидата,
 * завершить голосование и подсчитать голоса (выбрать мэра).
 */
public class StartElectionsDaoImpl implements StartElectionsDao {
    private final Database database;

    public StartElectionsDaoImpl() {
        database = Database.getInstance();
    }

    /**
     * Старт голосования.
     *
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае отсутствия прав для начала голосования (проверка токена).
     */
    @Override
    public UUID setElectionsStarted(UUID token) throws ElectionsException {
        if (!token.equals(database.getAdminToken())) {
            throw new ElectionsException(ExceptionErrorCode.NOT_ENOUGH_ROOT);
        }
        database.setElectionsStatus(ElectionsStatus.ELECTIONS_STARTED);
        for (Candidate candidate : database.getCandidateMap().values()) {
            if (candidate.isAgreement()
                    && !candidate.getCandidateProgram().getProposals().isEmpty()) {
                database.getCandidatesForMajor().putIfAbsent(candidate.getFullName(), 0);
            }
        }
        database.getCandidatesForMajor().put("Against All", 0); //добавялем кандидата против всех
        return token;
    }

    /**
     * Голосование за определенного кандидата.
     *
     * @param token             Идентификатор избирателя осуществляющего запрос.
     * @param candidateFullName Кандидат за которого голосует избиратель.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно.
     * @throws ElectionsException выбрасывает исключение в случае если выборы еще не начались,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором,
     *                            в случае ненахождения кандидата в базе.
     */
    @Override
    public UUID voteForCandidate(UUID token, String candidateFullName) throws ElectionsException { //добавить проверку на повторное голосование
        if (!database.getElectionsStatus().equals(ElectionsStatus.ELECTIONS_STARTED)) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_NOT_STARTED);
        }
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        if (database.getCandidatesForMajor().containsKey(candidateFullName)) {
            database.getCandidatesForMajor().put(candidateFullName, database.getCandidatesForMajor().get(candidateFullName) + 1); //голосуем за выбранного кандидата
            return token;
        }
        throw new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_LIST);
    }

    /**
     * Окончание голосования и подсчет голосов.
     *
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает полное имя кандидата который по результатам подсчета был избран мэром (если был).
     * @throws ElectionsException выбрасывает исключение в случае если выборы еще не начались,
     *                            в случае отсутствия прав для окончания голосования (проверка токена).
     *                            в случае если при подсчете голосов мэр не получил большинство голосов.
     */
    @Override
    public String chooseMajor(UUID token) throws ElectionsException {
        if (!database.getElectionsStatus().equals(ElectionsStatus.ELECTIONS_STARTED)) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_NOT_STARTED);
        }
        if (!token.equals(database.getAdminToken())) {
            throw new ElectionsException(ExceptionErrorCode.NOT_ENOUGH_ROOT);
        }
        final int max = Collections.max(database.getCandidatesForMajor().values());
        //если у нескольких кандидатов одинаковое количество голосов - мэр не выбран
        if (Collections.frequency(database.getCandidatesForMajor().values(), max) > 1) {
            throw new ElectionsException(ExceptionErrorCode.MAJOR_NOT_SELECTED);
        }
        //если у кандидата "против всех" больше всего голосов - мэр не выбран
        if (database.getCandidatesForMajor().get("Against All") == max) {
            throw new ElectionsException(ExceptionErrorCode.MAJOR_NOT_SELECTED);
        }
        for (Map.Entry<String, Integer> pair : database.getCandidatesForMajor().entrySet()) {
            if (pair.getValue().equals(max)) {
                database.setElectionsStatus(ElectionsStatus.ELECTIONS_ENDED);
                return pair.getKey();
            }
        }
        throw new ElectionsException(ExceptionErrorCode.SOMETHING_WRONG);
    }
}

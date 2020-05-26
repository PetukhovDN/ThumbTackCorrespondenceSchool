package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.enums.ElectionsStatus;
import net.thumbtack.school.elections.enums.ResultsOfRequests;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorInfo;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Voter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * DataAccessObject для работы с избирателями.
 * Методы:
 * Регистрация избирателя,
 * логаут избирателя (выход с сервера с возможностью вернуться),
 * логин избирателя (возврат на сервер избирателя вышедшего с него),
 */
public class VoterDaoImpl implements VoterDao {
    private final Database database;

    public VoterDaoImpl() {
        database = Database.getInstance();
    }

    /**
     * Регистрация нового избирателя в базе.
     *
     * @param voter Избиратель который регистрируется.
     * @return возвращает идентификатор зарегестрировавшегося избирателя.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от уже зарегестрировавшегося пользователя.
     */
    @Override
    public UUID insertToDataBase(Voter voter) throws ElectionsException {
        if (database.getElectionsStatus().equals(ElectionsStatus.ELECTIONS_STARTED)) {
            throw new ElectionsException(ExceptionErrorInfo.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (database.getRegisteredVoters().contains(voter)) {
            throw new ElectionsException(ExceptionErrorInfo.DUPLICATE_VOTER);
        }
        database.getVotersMap().put(voter.getToken(), voter);
        database.getValidTokens().add(voter.getToken());

        database.getRegisteredVoters().add(voter);
        return voter.getToken();
    }

    /**
     * Выход с сервера (отказ от участия в голосовании).
     *
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает сообщение об успешном завершении операции.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором.
     */
    @Override
    public ResultsOfRequests logoutFromDatabase(UUID token) throws ElectionsException {
        if (database.getElectionsStatus().equals(ElectionsStatus.ELECTIONS_STARTED)) {
            throw new ElectionsException(ExceptionErrorInfo.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorInfo.WRONG_VOTER_TOKEN);
        }
        database.getVotersMap().get(token).setToken(null);
        database.getValidTokens().remove(token);
        database.getRegisteredVoters().remove(database.getVotersMap().get(token));

        if (database.getCandidateMap().containsKey(token)) { //если является кандидатом
            database.getCandidateMap().get(token).setAgreement(false); //отказаться им быть
        }

        for (Proposal proposal : database.getProposalMap().values()) {
            if (proposal.getAuthorToken().equals(token)) { //если является автором предложения,
                proposal.getRating().remove(token); //удалить рейтинг этого предложения
                proposal.setDefaultAuthor(); //назначить автором всё общество города
            }
        }
        return ResultsOfRequests.SUCCESSFUL_REQUEST;
    }

    /**
     * Возврат обратно на сервер ушедшего ранее с него избирателя.
     *
     * @param login    Логин избирателя, покинувшего сервер (радлогинившегося).
     * @param password Пароль избирателя, покинувшего сервер (радлогинившегося).
     * @return возвращает новый идентификатор вернувгемуся на голосование избирателю.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с введенным неверным паролем,
     *                            в случае отсутствия пользователя с таким логином в базе.
     */
    @Override
    public UUID loginToDatabase(String login, String password) throws ElectionsException {
        if (database.getElectionsStatus().equals(ElectionsStatus.ELECTIONS_STARTED)) {
            throw new ElectionsException(ExceptionErrorInfo.ELECTIONS_HAVE_BEEN_STARTED);
        }
        for (Map.Entry<UUID, Voter> pair : database.getVotersMap().entrySet()) {
            final Voter voter = pair.getValue();
            if (voter.getLogin().equals(login) && voter.getPassword().equals(password)) { //проверяет есть ли такой избиратель и верный ли пароль
                voter.setToken(UUID.randomUUID()); //назначает новый случайный token для этого избирателя
                database.getVotersMap().put(voter.getToken(), voter); //добавить в базу избирателя с новым токеном
                database.getValidTokens().add(voter.getToken()); //добавить токен в список валидных
                database.getVotersMap().remove(pair.getKey()); //удалить из базы избирателя со старым невалидным токеном
                return pair.getKey();
            }
        }
        throw new ElectionsException(ExceptionErrorInfo.WRONG_VOTER_LOGIN);
    }

    /**
     * Получение списка всех зарегестрировавшихся на сервере избирателей.
     *
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return список всех участвующих в голосовании ищбирателей.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов.
     */
    @Override
    public List<Voter> getAllVotersFromDatabase(UUID token) throws ElectionsException {
        if (!database.getValidTokens().contains(token)) {
            throw new ElectionsException(ExceptionErrorInfo.WRONG_VOTER_TOKEN);
        }
        return new ArrayList<>(database.getVotersMap().values());
    }
}




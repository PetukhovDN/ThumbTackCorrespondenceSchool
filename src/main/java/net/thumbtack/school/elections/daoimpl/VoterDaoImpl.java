package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Voter;

import java.util.Set;
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
     * @param voter Избиратель который регистрируется.
     * @return возвращает идентификатор зарегестрировавшегося избирателя.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от уже зарегестрировавшегося пользователя.
     */
    @Override
    public UUID insertToDataBase(Voter voter) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (database.getVotersMap().containsKey(voter)) {
            throw new ElectionsException(ExceptionErrorCode.DUPLICATE_VOTER);
        }
        database.getVotersMap().put(voter, voter.getToken());
        return voter.getToken();
    }

    /**
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return возвращает идентификатор избирателя если запрос был осуществлен успешно (токен больше невалидный).
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с невалидным идентификатором.
     */
    @Override
    public UUID logoutFromDatabase(UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getVotersMap().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Voter voter : database.getVotersMap().keySet()) {
            /*
             REVU Поиск элемента обходом всех ключей Map в цикле это длительная операция.
             Map позволяет быстро (за константное время) искать значения по ключу. В данном случае это главное
             преимущество Map перед List.
             Вы заменили List на Map в классе Database. Но сейчас вы не используете преимущество Map перед List.
             Подумайте о том, что можно изменить в текущем подходе в классе Database, чтобы избежать циклов в DAO.
             Например можно использовать другое значение как ключ в Map, чтобы получать нужный элемент однократным
             вызовом get().
             В общем случае лучший способ работы с Map, это вызов методов get и put. Вы уже использовали подобный подход
             в задании 10.
             Следует избегать циклов по ключам / значениям Map.
            */
            if (voter.getToken().equals(token)) { //проверяет есть ли такой избиратель
                for (Candidate candidate : database.getCandidateMap().keySet()) {
                    if (candidate.getFirstName().equals(voter.getFirstName()) //проверяем ли такой согласившийся кандидат
                            && candidate.getLastName().equals(voter.getLastName())) {
                        database.getCandidateMap().put(candidate, false);
                        break;
                    }
                }
                voter.setToken(null); //удаляем данный token
                database.getVotersMap().put(voter, voter.getToken());
                break;
            }
        }
        for (Proposal proposal : database.getProposalSet()) {
            if (proposal.getAuthorToken().equals(token)) { //если является автором предложения,
                proposal.getRating().remove(token); //удалить рейтинг этого предложения
                proposal.setDefaultAuthor(); //назначить автором всё общество города
            }
        }
        return token;
    }

    /**
     * @param login    Логин избирателя, покинувшего сервер (радлогинившегося).
     * @param password Пароль избирателя, покинувшего сервер (радлогинившегося).
     * @return возвращает новый идентификатор вернувгемуся на голосование избирателю.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов,
     *                            при попытке осуществления запроса от пользователя с введенным неверным паролем,
     *                            в случае отсутствия пользователя с таким логином в базе.
     */
    @Override
    public UUID loginToDatabase(String login, String password) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        for (Voter voter : database.getVotersMap().keySet()) {
            if (voter.getLogin().equals(login)) { //проверяет есть ли такой избиратель
                if (voter.getPassword().equals(password)) { //проверяет верный ли пароль
                    voter.setToken(UUID.randomUUID()); //назначает новый случайный token для этого избирателя
                    database.getVotersMap().put(voter, voter.getToken());
                    return voter.getToken();
                } else {
                    throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_PASSWORD);
                }
            } else {
                throw new ElectionsException(ExceptionErrorCode.NULL_VOTER_LOGIN);
            }
        }
        return null; //исправить
    }

    /**
     * @param token Идентификатор избирателя осуществляющего запрос.
     * @return список всех участвующих в голосовании ищбирателей.
     * @throws ElectionsException выбрасывает исключение в случае начала выборов.
     */
    @Override
    public Set<Voter> getAllVotersFromDatabase(UUID token) throws ElectionsException {
        if (!database.getVotersMap().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        return database.getVotersMap().keySet();
    }
}




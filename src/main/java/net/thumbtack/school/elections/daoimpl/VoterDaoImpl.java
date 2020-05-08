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
 * DataAccessObject для работы с ищбирателями.
 * Методы:
 * Регистрация избирателя,
 * логаут избирателя (выход с сервера с возможностью вернуться),
 * логин избирателя (возврат на сервер избирателя вышедшего с него),
 *
 */
public class VoterDaoImpl implements VoterDao {
    private final Database database;

    public VoterDaoImpl() {
        database = Database.getInstance();
    }

    @Override
    public UUID insertToDataBase(Voter voter) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (database.getVotersMap().containsKey(voter)) {  //проверяет нет ли уже такого избирателя
            throw new ElectionsException(ExceptionErrorCode.DUPLICATE_VOTER);
        }
        database.getVotersMap().put(voter, voter.getToken());
        return voter.getToken(); //возвращает значение токена избирателя
    }

    @Override
    public UUID logoutFromDatabase(UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getVotersMap().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Voter voter : database.getVotersMap().keySet()) {
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

    @Override
    public Set<Voter> getAllVotersFromDatabase(UUID token) throws ElectionsException {
        if (!database.getVotersMap().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        return database.getVotersMap().keySet();
    }
}




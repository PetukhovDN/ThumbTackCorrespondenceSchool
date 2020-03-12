package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Voter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VoterDaoImpl implements VoterDao {
    Database database = Database.getInstance(); //открываем базу

    @Override
    public UUID insertToDataBase(Voter voter) throws ElectionsException {
        if (database.getVotersList().contains(voter)) {  //проверяет нет ли уже такого избирателя
            throw new ElectionsException(ExceptionErrorCode.DUPLICATE_VOTER);
        }
        database.getVotersList().add(voter); //добавляет избирателя в set
        database.getLoginVotersSet().add(voter.getLogin()); //добавляет логи избирателя в список залогинившихся пользователей
        return voter.getToken(); //возвращает значение токена избирателя
    }

    @Override
    public UUID loginToDatabase(String login, String password) throws ElectionsException {
        UUID result = null;
        for (Voter voter : database.getVotersList()
        ) {
            if (voter.getLogin().equals(login)) { //проверяет есть ли такой избиратель
                if (voter.getPassword().equals(password))//проверяет верен ли пароль
                {
                    voter.setToken(UUID.randomUUID()); //назначает новый случайный token для этого избирателя
                } else {
                    throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_PASSWORD);
                }
                result = voter.getToken();
                database.getLoginVotersSet().add(voter.getLogin()); //добавляет логин в set залогинившихся избирателей
            } else {
                throw new ElectionsException(ExceptionErrorCode.NULL_VOTER_LOGIN);
            }
        }
        return result;
    }

    @Override
    public UUID logoutFromDatabase(UUID token) throws ElectionsException {
        UUID result = null;
        for (Voter voter : database.getVotersList()
        ) {
            if (voter.getToken().equals(token)) { //проверяет есть ли такой избиратель
                voter.setToken(null); //удаляем данный token
                result = voter.getToken();
                database.getLoginVotersSet().remove(voter.getLogin()); //удаляет логин из сета залогинивщихся избирателей
            } else {
                throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
            }
        }
        return result;
    }

    @Override
    public List<Voter> getAllVoters(UUID token) throws ElectionsException {
        List<Voter> result = new ArrayList<>();
        for (Voter voter : database.getVotersList()
        ) {
            if (voter.getToken().equals(token)) { //только зарегестрировавшиеся избиратели могут получить список всех избирателей?
                result.addAll(database.getVotersList());
            } else {
                throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
            }
        }
        return result;
    }
}


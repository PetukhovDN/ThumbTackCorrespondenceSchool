package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.VoterException;
import net.thumbtack.school.elections.model.Voter;

import java.util.UUID;

public class VoterDaoImpl implements VoterDao {
    Database database = Database.getInstance(); //проверяет создана ли база данных

    @Override
    public UUID insertToDataBase(Voter voter) throws VoterException {
        return database.insertToVoterSet(voter); //возвращает токен избирателя добавленного в set
    }

    @Override
    public UUID loginToDatabase(String login, String password) throws VoterException {
        return database.loginToDatabase(login, password); //возвращает данные избирателя если он есть
    }

    @Override
    public boolean logoutFromDatabase(UUID token) throws VoterException {
        return database.logoutFromDatabase(token) == null; //удаляет избирателя из списка залогинившихся и удаляет его token
    }
}


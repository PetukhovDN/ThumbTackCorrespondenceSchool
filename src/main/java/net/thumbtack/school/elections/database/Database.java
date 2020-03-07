package net.thumbtack.school.elections.database;

import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.exceptions.VoterException;
import net.thumbtack.school.elections.model.Voter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Database implements Serializable {

    private static final long serialVersionUID = 577745366661255865L;
    private static Database instance;

    private Set<Voter> votersSet = new HashSet<>();
    private Set<String> loginVotersSet = new HashSet<>();

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Set<Voter> getVotersSet() {
        return votersSet;
    }

    public Set<String> getLoginVotersSet() {
        return loginVotersSet;
    }

    public UUID insertToVoterSet(Voter voter) throws VoterException {
        if (getVotersSet().contains(voter)) {  //проверяет нет ли уже такого избирателя
            throw new VoterException(ExceptionErrorCode.DUPLICATE_VOTER);
        }
        getVotersSet().add(voter); //добавляет избирателя в set
        getLoginVotersSet().add(voter.getLogin()); //добавляет логи избирателя в список залогинившихся пользователей
        return voter.getToken(); //возвращает значение токена избирателя
    }

    public UUID loginToDatabase(String login, String password) throws VoterException {
        UUID result = null;
        for (Voter voter : votersSet
        ) {
            if (voter.getLogin().equals(login)) { //проверяет есть ли такой избиратель
                if (voter.getPassword().equals(password))//проверяет верен ли пароль
                {
                    voter.setToken(UUID.randomUUID()); //назначает новый случайный token для этого избирателя
                } else {
                    throw new VoterException(ExceptionErrorCode.WRONG_VOTER_PASSWORD);
                }
                result = voter.getToken();
                getLoginVotersSet().add(voter.getLogin()); //добавляет логин в set залогинивщихся избирателей
            } else {
                throw new VoterException(ExceptionErrorCode.NULL_VOTER_LOGIN);
            }
        }
        return result;
    }

    public UUID logoutFromDatabase(UUID token) throws VoterException {
        UUID result = null;
        for (Voter voter : votersSet
        ) {
            if (voter.getToken().equals(token)) { //проверяет есть ли такой избиратель
                voter.setToken(null); //удаляем данный token
                result = voter.getToken();
                getLoginVotersSet().remove(voter.getLogin()); //удаляет логин из сета залогинивщихся избирателей
            } else {
                throw new VoterException(ExceptionErrorCode.EMPTY_VOTER_LOGIN);
            }
        }
        return result;
    }
}

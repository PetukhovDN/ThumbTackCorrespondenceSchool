package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Voter;

import java.util.Set;
import java.util.UUID;

public class VoterDaoImpl implements VoterDao {
    private final Database database;

    public VoterDaoImpl() {
        database = Database.getInstance();
    }

    @Override
    public UUID insertToDataBase(Voter voter) throws ElectionsException {
        if (database.getVotersList().containsKey(voter)) {  //проверяет нет ли уже такого избирателя
            throw new ElectionsException(ExceptionErrorCode.DUPLICATE_VOTER);
        }
        database.getVotersList().put(voter, voter.getToken());
        return voter.getToken(); //возвращает значение токена избирателя
    }

    @Override
    public UUID loginToDatabase(String login, String password) throws ElectionsException {
        for (Voter voter : database.getVotersList().keySet()) {
            if (voter.getLogin().equals(login) && voter.getPassword().equals(password)) { //проверяет есть ли такой избиратель
                voter.setToken(UUID.randomUUID()); //назначает новый случайный token для этого избирателя
                database.getVotersList().put(voter, voter.getToken());
                return voter.getToken();
            }
        }
        throw new ElectionsException(ExceptionErrorCode.NULL_VOTER_LOGIN);
    }

    @Override
    public UUID logoutFromDatabase(UUID token) throws ElectionsException {
        if (!database.getVotersList().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Voter voter : database.getVotersList().keySet()) {
            if (voter.getToken().equals(token)) { //проверяет есть ли такой избиратель
                voter.setToken(null); //удаляем данный token
                database.getVotersList().put(voter, null);
                break;
            }
        }
        for (Proposal proposal : database.getProposalList()) {
            if (proposal.getAuthorToken().equals(token)) { //если является автором предложения,
                proposal.getRating().remove(token); //удалить рейтинг этого предложения
            }
        }
        return token;
    }

    @Override
    public Set<Voter> getAllVotersFromDatabase(UUID token) throws ElectionsException {
        if (!database.getVotersList().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        return database.getVotersList().keySet();
    }
}




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

    @Override
    public UUID insertToDataBase(Voter voter) throws ElectionsException {
        if (Database.getInstance().getVotersList().containsKey(voter)) {  //проверяет нет ли уже такого избирателя
            throw new ElectionsException(ExceptionErrorCode.DUPLICATE_VOTER);
        }
        Database.getInstance().getVotersList().put(voter, voter.getToken());
        return voter.getToken(); //возвращает значение токена избирателя
    }

    @Override
    public UUID loginToDatabase(String login, String password) throws ElectionsException {
        for (Voter voter : Database.getInstance().getVotersList().keySet()) {
            if (voter.getLogin().equals(login) && voter.getPassword().equals(password)) { //проверяет есть ли такой избиратель
                voter.setToken(UUID.randomUUID()); //назначает новый случайный token для этого избирателя
                Database.getInstance().getVotersList().put(voter, voter.getToken());
                return voter.getToken();
            }
        }
        throw new ElectionsException(ExceptionErrorCode.NULL_VOTER_LOGIN);
    }

    @Override
    public UUID logoutFromDatabase(UUID token) throws ElectionsException {
        if (!Database.getInstance().getVotersList().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        //Database.getInstance().getVotersList().put()
        for (Voter voter : Database.getInstance().getVotersList().keySet()) {
            if (voter.getToken().equals(token)) { //проверяет есть ли такой избиратель
                voter.setToken(null); //удаляем данный token
                Database.getInstance().getVotersList().put(voter, null);
                break;
            }
        }
        for (Proposal proposal : Database.getInstance().getProposalList()) {
            if (proposal.getAuthorToken().equals(token)) { //если является автором предложения,
                proposal.getRating().remove(token); //удалить рейтинг этого предложения
            }
        }
        return token;
    }

    @Override
    public Set<Voter> getAllVotersFromDatabase(UUID token) throws ElectionsException {
        if (!Database.getInstance().getVotersList().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        return Database.getInstance().getVotersList().keySet();
    }
}




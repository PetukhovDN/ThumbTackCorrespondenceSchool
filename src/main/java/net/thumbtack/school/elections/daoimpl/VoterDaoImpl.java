package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Voter;

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
        database.getValidTokensSet().add(voter.getToken()); //добавляет токен в сет валидных токенов
        return voter.getToken(); //возвращает значение токена избирателя
    }

    @Override
    public UUID loginToDatabase(String login, String password) throws ElectionsException {
        for (Voter voter : database.getVotersList()) {
            if (voter.getLogin().equals(login) && voter.getPassword().equals(password)) { //проверяет есть ли такой избиратель
                voter.setToken(UUID.randomUUID()); //назначает новый случайный token для этого избирателя
                database.getValidTokensSet().add(voter.getToken());
                return voter.getToken();
            }
        }
        throw new ElectionsException(ExceptionErrorCode.NULL_VOTER_LOGIN);
    }

    @Override
    public UUID logoutFromDatabase(UUID token) throws ElectionsException {
        if (database.getValidTokensSet().contains(token)) {
            for (Voter voter : database.getVotersList()) {
                if (voter.getToken().equals(token)) { //проверяет есть ли такой избиратель
                    database.getValidTokensSet().remove(token);
                    voter.setToken(null); //удаляем данный token
                    break;
                }
            }
            for (Proposal proposal: database.getProposalList()) {
                if (proposal.getAuthorToken().equals(token)){ //если является автором предложения,
                    proposal.getRating().remove(token); //удалить рейтинг этого предложения
                }
            }
            return token;
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public List<Voter> getAllVotersFromDatabase(UUID token) throws ElectionsException {
        if (database.getValidTokensSet().contains(token)) {
            return database.getVotersList();
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }
}



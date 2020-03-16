package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.Voter;

import java.util.UUID;

public class CandidateDaoImpl implements CandidateDao {
    Database database = Database.getInstance(); //открываем базу

    @Override
    public UUID addCandidateToDatabase(Candidate candidate, UUID token) throws ElectionsException {
        if (database.getValidTokensSet().contains(token)) {
            for (Voter voter : database.getVotersList()
            ) {
                if (voter.getFirstName().equals(candidate.getFirstName()) && voter.getLastName().equals(candidate.getLastName())) { //проверяет есть ли такой избиратель
                    if (voter.getToken().equals(token)) {                   //если избиратель выдвигает сам себя,
                        database.getCandidatesList().put(candidate, true);  //то он автоматически дает свое согласие
                    } else {
                        database.getCandidatesList().put(candidate, false);
                    }
                    return token;
                }
            }
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }
}

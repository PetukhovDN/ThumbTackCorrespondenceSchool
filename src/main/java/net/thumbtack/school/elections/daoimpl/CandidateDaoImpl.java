package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.Voter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CandidateDaoImpl implements CandidateDao {
    Database database = Database.getInstance(); //открываем базу

    @Override
    public UUID addCandidateToDatabase(Candidate candidate, UUID token) throws ElectionsException {
        if (database.getValidTokensSet().contains(token)) {
            for (Voter voter : database.getVotersList()) {
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

    @Override
    public UUID agreeToBeCandidate(UUID token) throws ElectionsException {
        if (database.getValidTokensSet().contains(token)) {
            for (Voter voter : database.getVotersList()) {
                if (voter.getToken().equals(token)) {
                    for (Candidate candidate : database.getCandidatesList().keySet()) {
                        if (candidate.getFirstName().equals(voter.getFirstName()) && candidate.getLastName().equals(voter.getLastName())) {
                            database.getCandidatesList().put(candidate, true);
                            return token;
                        } else {
                            throw new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_LIST);
                        }
                    }
                }
            }
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public List<Candidate> getAllAgreedCandidates(UUID token) throws ElectionsException {
        List<Candidate> resultList = new ArrayList<>();
        if (database.getValidTokensSet().contains(token)) {
            for (Map.Entry<Candidate, Boolean> pair : database.getCandidatesList().entrySet()) {
                if (pair.getValue()) {
                    resultList.add(pair.getKey());
                }
            }
            return resultList;
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }
}
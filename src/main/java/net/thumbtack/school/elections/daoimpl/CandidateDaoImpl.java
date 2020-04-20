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

    @Override
    public UUID addCandidateToDatabase(Candidate candidate, UUID token) throws ElectionsException {
        if (!Database.getInstance().getVotersList().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Map.Entry<Voter, UUID> voterPair : Database.getInstance().getVotersList().entrySet()) {
            if (voterPair.getKey().getFirstName().equals(candidate.getFirstName())
                    && voterPair.getKey().getLastName().equals(candidate.getLastName())) {
                if (voterPair.getKey().getToken().equals(token)) {
                    Database.getInstance().getCandidatesList().put(candidate, true);
                } else {
                    Database.getInstance().getCandidatesList().put(candidate, false);
                }
                return token;
            }
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public UUID agreeToBeCandidate(UUID token) throws ElectionsException {
        for (Map.Entry<Voter, UUID> voterPair : Database.getInstance().getVotersList().entrySet()) {
            if (voterPair.getValue().equals(token)) {
                for (Map.Entry<Candidate, Boolean> candidatePair : Database.getInstance().getCandidatesList().entrySet()) {
                    if (candidatePair.getKey().getFirstName().equals(voterPair.getKey().getFirstName())
                            && candidatePair.getKey().getLastName().equals(voterPair.getKey().getLastName())) {
                        Database.getInstance().getCandidatesList().put(candidatePair.getKey(), true);
                        return token;
                    }
                }
                throw new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_LIST);
            }
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public List<Candidate> getAllAgreedCandidates(UUID token) throws ElectionsException {
        List<Candidate> resultList = new ArrayList<>();
        if (!Database.getInstance().getVotersList().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Map.Entry<Candidate, Boolean> pair : Database.getInstance().getCandidatesList().entrySet()) {
            if (pair.getValue()) {
                resultList.add(pair.getKey());
            }
        }
        return resultList;
    }
}

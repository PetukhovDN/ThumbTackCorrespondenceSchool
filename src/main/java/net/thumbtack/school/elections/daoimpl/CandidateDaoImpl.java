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
    private final Database database;

    public CandidateDaoImpl() {
        database = Database.getInstance();
    }

    @Override
    public UUID addCandidateToDatabase(Candidate candidate, UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        if (!database.getVotersMap().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Map.Entry<Voter, UUID> voterPair : database.getVotersMap().entrySet()) {
            if (voterPair.getKey().getFirstName().equals(candidate.getFirstName())
                    && voterPair.getKey().getLastName().equals(candidate.getLastName())) {
                if (voterPair.getKey().getToken().equals(token)) {
                    database.getCandidateMap().put(candidate, true);
                } else {
                    database.getCandidateMap().put(candidate, false);
                }
                return token;
            }
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public UUID agreeToBeCandidate(UUID token) throws ElectionsException {
        if (database.getElectionsStarted().equals("Выборы начались")) {
            throw new ElectionsException(ExceptionErrorCode.ELECTIONS_HAVE_BEEN_STARTED);
        }
        for (Map.Entry<Voter, UUID> voterPair : database.getVotersMap().entrySet()) {
            if (voterPair.getValue().equals(token)) {
                for (Candidate candidate : database.getCandidateMap().keySet()) {
                    if (candidate.getFirstName().equals(voterPair.getKey().getFirstName())
                            && candidate.getLastName().equals(voterPair.getKey().getLastName())) {
                        database.getCandidateMap().put(candidate, true);
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
        if (!database.getVotersMap().containsValue(token)) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (Map.Entry<Candidate, Boolean> pair : database.getCandidateMap().entrySet()) {
            if (pair.getValue()) {
                resultList.add(pair.getKey());
            }
        }
        return resultList;
    }
}

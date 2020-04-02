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
        if (Database.getInstance().getValidTokensSet().contains(token)) { // REVU Если инвертировать условие и сразу возвращать результат, то у вас "уменьшится отступ" всего метода. Актуально для всех DAO.
            for (Voter voter : Database.getInstance().getVotersList()) {
                if (voter.getFirstName().equals(candidate.getFirstName())
                        && voter.getLastName().equals(candidate.getLastName())) { //проверяет есть ли такой избиратель
                    if (voter.getToken().equals(token)) {                                   //если избиратель выдвигает сам себя,
                        Database.getInstance().getCandidatesList().put(candidate, true);    //то он автоматически дает свое согласие
                    }
                    else {
                        Database.getInstance().getCandidatesList().put(candidate, false);
                    }
                    return token;
                }
            }
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public UUID agreeToBeCandidate(UUID token) throws ElectionsException {
        if (Database.getInstance().getValidTokensSet().contains(token)) {
            for (Voter voter : Database.getInstance().getVotersList()) { // REVU Такой подход работы со списками очень неэффективный. Используйте Map.
                if (voter.getToken().equals(token)) {
                    List<Candidate> list = new ArrayList<>(Database.getInstance().getCandidatesList().keySet()); // REVU Используйте Map.
                    for (Candidate candidate : list) {
                        if (candidate.getFirstName().equals(voter.getFirstName())
                                && candidate.getLastName().equals(voter.getLastName())) {
                            Database.getInstance().getCandidatesList().put(candidate, true);
                            return token;
                        }
                        throw new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_LIST);

                    }

                }
            }
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public List<Candidate> getAllAgreedCandidates(UUID token) throws ElectionsException {
        List<Candidate> resultList = new ArrayList<>();
        if (Database.getInstance().getValidTokensSet().contains(token)) {
            for (Map.Entry<Candidate, Boolean> pair : Database.getInstance().getCandidatesList().entrySet()) {
                if (pair.getValue()) {
                    resultList.add(pair.getKey());
                }
            }
            return resultList;
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }
}
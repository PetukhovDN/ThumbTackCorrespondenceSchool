package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ProposalDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Proposal;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProposalDaoImpl implements ProposalDao {
    Database database = Database.getInstance(); //открываем базу

    @Override
    public UUID makeProposal(Proposal proposal, UUID token) throws ElectionsException {
        if (database.getValidTokensSet().contains(token)) {
            database.getProposalList().add(proposal);
            return token;
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public UUID addRatingForProposal(String proposal, int rate, UUID token) throws ElectionsException {
        if (database.getValidTokensSet().contains(token)) {
            for (Proposal proposalFromBase : database.getProposalList()) {
                if (proposalFromBase.getProposalInfo().equals(proposal)) {
                    if (proposalFromBase.getAuthorToken().equals(token)) {
                        throw new ElectionsException(ExceptionErrorCode.SAME_PROPOSAL_AUTHOR);
                    } else {
                        proposalFromBase.getRating().put(token, rate);
                        return token;
                    }
                }
            }
            throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_INFO);
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public UUID removeRatingFromProposal(String proposal, UUID token) throws ElectionsException {
        if (database.getValidTokensSet().contains(token)) {
            for (Proposal proposalFromBase : database.getProposalList()) {
                if (proposalFromBase.getProposalInfo().equals(proposal)) {
                    if (proposalFromBase.getAuthorToken().equals(token)) {
                        throw new ElectionsException(ExceptionErrorCode.SAME_PROPOSAL_AUTHOR);
                    } else {
                        proposalFromBase.getRating().remove(token);
                        return token;
                    }
                }
            }
            throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_INFO);
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }

    @Override
    public Map<String, Integer> getAllProposals(UUID token) throws ElectionsException {
        Map<String, Integer> results = new HashMap<>();
        if (database.getValidTokensSet().contains(token)) {
            for (Proposal proposal : database.getProposalList()) {
                int sumRatings = 0;
                int count = 0;
                for (Integer rating : proposal.getRating().values()) {
                    sumRatings += rating;
                    count++;
                }
                results.put(proposal.getProposalInfo(), sumRatings / count);
            }
            return results;
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }
}

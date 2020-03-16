package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ProposalDao;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Proposal;

import java.util.UUID;

public class ProposalDaoImpl implements ProposalDao {
    Database database = Database.getInstance(); //открываем базу

    @Override
    public UUID makeProposal(Proposal proposal) throws ElectionsException {
        if (database.getValidTokensSet().contains(proposal.getAuthorToken())) {
            database.getProposalList().add(proposal);
            return proposal.getAuthorToken();
        }
        throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
    }
}

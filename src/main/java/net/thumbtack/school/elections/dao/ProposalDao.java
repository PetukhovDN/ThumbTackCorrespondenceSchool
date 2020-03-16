package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Proposal;

import java.util.UUID;

public interface ProposalDao {
    UUID makeProposal(Proposal proposal) throws ElectionsException;

}

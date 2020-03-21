package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Proposal;

import java.util.Map;
import java.util.UUID;

public interface ProposalDao {
    UUID makeProposal(Proposal proposal, UUID token) throws ElectionsException;

    UUID addRatingForProposal(String proposal, int rate, UUID token) throws ElectionsException;

    UUID removeRatingFromProposal(String proposal, UUID token) throws ElectionsException;

    Map<String, Integer> getAllProposals(UUID token) throws ElectionsException;

}

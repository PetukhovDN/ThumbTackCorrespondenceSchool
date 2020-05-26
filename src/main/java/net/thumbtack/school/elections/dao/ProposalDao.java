package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.enums.ResultsOfRequests;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.CandidateProgram;
import net.thumbtack.school.elections.model.Proposal;

import java.util.Map;
import java.util.UUID;

public interface ProposalDao {
    ResultsOfRequests makeProposal(Proposal proposal, UUID token) throws ElectionsException;

    ResultsOfRequests addRating(String proposal, int rate, UUID token) throws ElectionsException;

    ResultsOfRequests removeRating(String proposal, UUID token) throws ElectionsException;

    Map<String, Double> getAllProposalsWithRate(UUID token) throws ElectionsException;

    Map<String, CandidateProgram> getAllProposalsFromVoter(UUID token, String[] voterNames) throws ElectionsException;

}

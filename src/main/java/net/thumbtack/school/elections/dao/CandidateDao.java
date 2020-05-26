package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.enums.ResultsOfRequests;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.CandidateProgram;

import java.util.Map;
import java.util.UUID;

public interface CandidateDao {
    ResultsOfRequests addCandidateToDatabase(Candidate candidate, UUID token) throws ElectionsException;

    ResultsOfRequests agreeToBeCandidate(UUID token) throws ElectionsException;

    ResultsOfRequests addProposalToCandidateProgram(String proposal, UUID token) throws ElectionsException;

    ResultsOfRequests removeProposal(String proposal, UUID token) throws ElectionsException;

    Map<Candidate, CandidateProgram> getAllAgreedCandidates(UUID token) throws ElectionsException;

}

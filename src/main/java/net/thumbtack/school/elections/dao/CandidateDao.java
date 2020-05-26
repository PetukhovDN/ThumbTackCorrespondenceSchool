package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.CandidateProgram;

import java.util.Map;
import java.util.UUID;

public interface CandidateDao {
    UUID addCandidateToDatabase(Candidate candidate, UUID token) throws ElectionsException;

    UUID agreeToBeCandidate(UUID token) throws ElectionsException;

    UUID addProposalToCandidateProgram(String proposal, UUID token) throws ElectionsException;

    UUID removeProposal(String proposal, UUID token) throws ElectionsException;

    Map<Candidate, CandidateProgram> getAllAgreedCandidates(UUID token) throws ElectionsException;

}

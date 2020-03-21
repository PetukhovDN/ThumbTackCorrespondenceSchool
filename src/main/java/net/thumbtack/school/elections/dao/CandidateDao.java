package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Candidate;

import java.util.List;
import java.util.UUID;

public interface CandidateDao {
    UUID addCandidateToDatabase(Candidate candidate, UUID token) throws ElectionsException;

    UUID agreeToBeCandidate(UUID token) throws ElectionsException;

    List<Candidate> getAllAgreedCandidates(UUID token) throws ElectionsException;

}

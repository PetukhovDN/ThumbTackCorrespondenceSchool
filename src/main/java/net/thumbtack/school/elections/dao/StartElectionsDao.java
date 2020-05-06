package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Candidate;

import java.util.UUID;

public interface StartElectionsDao {
    UUID setElectionsStarted(UUID token) throws ElectionsException;

    UUID voteForCandidate(UUID token, Candidate candidate) throws ElectionsException;

    Candidate chooseMajor(UUID token) throws ElectionsException;
}

package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.exceptions.ElectionsException;

import java.util.UUID;

public interface StartElectionsDao {
    UUID setElectionsStarted(UUID token) throws ElectionsException;

    UUID voteForCandidate(UUID token, String candidateFullName) throws ElectionsException;

    String chooseMajor(UUID token) throws ElectionsException;
}

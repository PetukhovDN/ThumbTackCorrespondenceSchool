package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.enums.ResultsOfRequests;
import net.thumbtack.school.elections.exceptions.ElectionsException;

import java.util.UUID;

public interface StartElectionsDao {
    ResultsOfRequests setElectionsStarted(UUID token) throws ElectionsException;

    ResultsOfRequests voteForCandidate(UUID token, String candidateFullName) throws ElectionsException;

    String chooseMajor(UUID token) throws ElectionsException;
}

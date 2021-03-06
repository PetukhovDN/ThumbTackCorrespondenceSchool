package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.enums.ResultsOfRequests;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Voter;

import java.util.List;
import java.util.UUID;

public interface VoterDao {
    UUID insertToDataBase(Voter voter) throws ElectionsException;

    UUID loginToDatabase(String login, String password) throws ElectionsException;

    ResultsOfRequests logoutFromDatabase(UUID token) throws ElectionsException;

    List<Voter> getAllVotersFromDatabase(UUID token) throws ElectionsException;

}

package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Voter;

import java.util.Set;
import java.util.UUID;

public interface VoterDao {
    UUID insertToDataBase(Voter voter) throws ElectionsException;

    UUID loginToDatabase(String login, String password) throws ElectionsException;

    UUID logoutFromDatabase(UUID token) throws ElectionsException;

    Set<Voter> getAllVotersFromDatabase(UUID token) throws ElectionsException;

}

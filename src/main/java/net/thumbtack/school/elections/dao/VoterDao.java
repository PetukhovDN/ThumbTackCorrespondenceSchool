package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Voter;

import java.util.List;
import java.util.UUID;

public interface VoterDao {
    UUID insertToDataBase(Voter voter) throws ElectionsException;

    UUID loginToDatabase(String login, String password) throws ElectionsException;

    UUID logoutFromDatabase(UUID token) throws ElectionsException;

    List<Voter> getAllVoters(UUID token) throws ElectionsException;

}

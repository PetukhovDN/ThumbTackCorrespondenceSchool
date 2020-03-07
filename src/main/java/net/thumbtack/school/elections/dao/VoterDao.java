package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.exceptions.VoterException;
import net.thumbtack.school.elections.model.Voter;

import java.util.UUID;

public interface VoterDao {
    UUID insertToDataBase(Voter voter) throws VoterException;

    UUID loginToDatabase(String login, String password) throws VoterException;

    boolean logoutFromDatabase(UUID token) throws VoterException;

}

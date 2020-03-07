package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.VoterException;

import java.util.UUID;

public class LogoutVoterDtoRequest {

    private UUID token;

    public UUID getToken() {
        return token;
    }

    public void validate() throws VoterException {

    }
}

package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class RegisterVoterResponse {

    private UUID token;

    public RegisterVoterResponse(UUID token) {
        this.token = token;

    }

    public UUID getToken() {
        return token;
    }
}

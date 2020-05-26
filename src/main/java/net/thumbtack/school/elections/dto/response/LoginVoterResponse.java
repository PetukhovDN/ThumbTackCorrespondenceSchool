package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class LoginVoterResponse {
    private UUID token;

    public LoginVoterResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class LogoutVoterResponse {
    private UUID token;

    public LogoutVoterResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

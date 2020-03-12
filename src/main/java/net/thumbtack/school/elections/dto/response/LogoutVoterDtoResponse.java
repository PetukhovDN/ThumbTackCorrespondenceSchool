package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class LogoutVoterDtoResponse {
    private UUID token;

    public LogoutVoterDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

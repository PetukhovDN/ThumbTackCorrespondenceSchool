package net.thumbtack.school.elections.dto.response.voterResponses;

import java.util.UUID;

public class LoginVoterDtoResponse {
    private UUID token;

    public LoginVoterDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

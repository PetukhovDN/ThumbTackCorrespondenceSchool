package net.thumbtack.school.elections.dto.response.voterResponses;

import java.util.UUID;

public class RegisterVoterDtoResponse {

    private UUID token;

    public RegisterVoterDtoResponse(UUID token) {
        this.token = token;

    }

    public UUID getToken() {
        return token;
    }
}

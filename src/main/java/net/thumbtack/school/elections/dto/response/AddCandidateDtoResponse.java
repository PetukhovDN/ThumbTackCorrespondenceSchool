package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class AddCandidateDtoResponse {
    private UUID token;

    public AddCandidateDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

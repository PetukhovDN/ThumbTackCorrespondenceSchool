package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class AgreeToBeCandidateDtoResponse {
    private UUID token;

    public AgreeToBeCandidateDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class AgreeToBeCandidateResponse {
    private UUID token;

    public AgreeToBeCandidateResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

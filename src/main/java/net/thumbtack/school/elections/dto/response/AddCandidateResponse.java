package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class AddCandidateResponse {
    private UUID token;

    public AddCandidateResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

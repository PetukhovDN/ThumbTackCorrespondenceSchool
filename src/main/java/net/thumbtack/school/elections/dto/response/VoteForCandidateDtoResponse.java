package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class VoteForCandidateDtoResponse {
    private UUID token;

    public VoteForCandidateDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

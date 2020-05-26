package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class VoteForCandidateResponse {
    private UUID token;

    public VoteForCandidateResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

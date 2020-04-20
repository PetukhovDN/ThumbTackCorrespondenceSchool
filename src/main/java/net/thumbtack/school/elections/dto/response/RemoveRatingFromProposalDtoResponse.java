package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class RemoveRatingFromProposalDtoResponse {
    private UUID token;

    public RemoveRatingFromProposalDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

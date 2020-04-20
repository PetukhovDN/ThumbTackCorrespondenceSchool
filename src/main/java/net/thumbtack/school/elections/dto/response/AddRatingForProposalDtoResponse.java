package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class AddRatingForProposalDtoResponse {
    private UUID token;

    public AddRatingForProposalDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

package net.thumbtack.school.elections.dto.response.proposalResponses;

import java.util.UUID;

public class MakeProposalDtoResponse {
    private UUID token;

    public MakeProposalDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class MakeProposalResponse {
    private UUID token;

    public MakeProposalResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

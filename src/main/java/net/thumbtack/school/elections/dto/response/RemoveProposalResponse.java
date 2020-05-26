package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class RemoveProposalResponse {
    private UUID token;

    public RemoveProposalResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

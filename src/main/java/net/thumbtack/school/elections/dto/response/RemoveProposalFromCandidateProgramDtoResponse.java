package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class RemoveProposalFromCandidateProgramDtoResponse {
    private UUID token;

    public RemoveProposalFromCandidateProgramDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

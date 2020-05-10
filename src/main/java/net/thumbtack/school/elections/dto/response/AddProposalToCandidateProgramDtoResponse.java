package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class AddProposalToCandidateProgramDtoResponse {
    private UUID token;

    public AddProposalToCandidateProgramDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

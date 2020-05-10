package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;

import java.util.UUID;

public class AddProposalToCandidateProgramDtoRequest {
    private UUID token;
    private String proposal;

    public AddProposalToCandidateProgramDtoRequest(UUID token, String proposal) {
        this.token = token;
        this.proposal = proposal;
    }

    public UUID getToken() {
        return token;
    }

    public String getProposal() {
        return proposal;
    }

    public void validate() throws ElectionsException {
        if (this.token == null || this.token.toString().isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
    }
}

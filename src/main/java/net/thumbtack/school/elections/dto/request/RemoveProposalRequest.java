package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorInfo;

import java.util.UUID;

public class RemoveProposalRequest {
    private UUID token;
    private String proposal;

    public RemoveProposalRequest(UUID token, String proposal) {
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
            throw new ElectionsException(ExceptionErrorInfo.WRONG_VOTER_TOKEN);
        }
    }
}

package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorInfo;

import java.util.UUID;

public class RemoveRatingRequest {
    private String proposal;
    private UUID token;

    public RemoveRatingRequest(String proposal, UUID token) {
        this.proposal = proposal;
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public String getProposal() {
        return proposal;
    }

    public void validate() throws ElectionsException {
        if (this.proposal == null || this.proposal.isEmpty()) {
            throw new ElectionsException(ExceptionErrorInfo.EMPTY_VOTER_PROPOSAL);
        }
        if (this.token == null || this.token.toString().isEmpty()) {
            throw new ElectionsException(ExceptionErrorInfo.WRONG_VOTER_TOKEN);
        }
    }
}

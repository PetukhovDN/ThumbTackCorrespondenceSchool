package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;

import java.util.UUID;

public class RemoveRatingFromProposalDtoRequest {
    private String proposal;
    private UUID token;

    public RemoveRatingFromProposalDtoRequest(String proposal, UUID token) {
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
            throw new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_PROPOSAL);
        }
        if (this.token == null || this.token.toString().isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
    }
}

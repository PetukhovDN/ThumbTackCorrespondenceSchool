package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;

import java.util.UUID;

public class AddRatingForProposalDtoRequest {
    private String proposal;
    private int rating;
    private UUID token;

    public AddRatingForProposalDtoRequest(String proposal, int rating, UUID token) {
        this.proposal = proposal;
        this.rating = rating;
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public String getProposal() {
        return proposal;
    }

    public int getRating() {
        return rating;
    }

    public void validate() throws ElectionsException {
        if (this.proposal == null || this.proposal.isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_PROPOSAL);
        }
        if (this.rating < 1 || this.rating > 5) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_PROPOSAL_RATING);
        }
        if (this.token == null || this.token.toString().isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
    }
}

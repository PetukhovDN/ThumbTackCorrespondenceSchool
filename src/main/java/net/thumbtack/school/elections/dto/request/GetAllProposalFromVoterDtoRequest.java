package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;

import java.util.UUID;

public class GetAllProposalFromVoterDtoRequest {
    private UUID token;
    private String[] voterFullNames;

    public GetAllProposalFromVoterDtoRequest(UUID token, String... voters) {
        this.token = token;
        this.voterFullNames = voters;
    }

    public UUID getToken() {
        return token;
    }

    public String[] getVoterFullNames() {
        return voterFullNames;
    }

    public void validate() throws ElectionsException {
        if (this.token == null || this.token.toString().isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
        for (String voterName : voterFullNames) {
            if (voterName == null || voterName.isEmpty()) {
                throw new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_FIRSTNAME);
            }
        }
    }
}

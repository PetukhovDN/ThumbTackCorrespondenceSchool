package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorInfo;

import java.util.UUID;

public class GetAllVoterProposalsRequest {
    private UUID token;
    private String[] votersFullNames;

    public GetAllVoterProposalsRequest(UUID token, String... votersFullNames) {
        this.token = token;
        this.votersFullNames = votersFullNames;
    }

    public UUID getToken() {
        return token;
    }

    public String[] getVotersFullNames() {
        return votersFullNames;
    }

    public void validate() throws ElectionsException {
        if (this.token == null || this.token.toString().isEmpty()) {
            throw new ElectionsException(ExceptionErrorInfo.WRONG_VOTER_TOKEN);
        }
        for (String voterName : votersFullNames) {
            if (voterName == null || voterName.isEmpty()) {
                throw new ElectionsException(ExceptionErrorInfo.EMPTY_VOTER_FIRSTNAME);
            }
        }
    }
}

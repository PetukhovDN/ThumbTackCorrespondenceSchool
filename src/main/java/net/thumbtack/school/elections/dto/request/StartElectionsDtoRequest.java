package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;

import java.util.UUID;

public class StartElectionsDtoRequest {
    private UUID token;
    private String electionsStarted;

    public StartElectionsDtoRequest(UUID token, String electionsStarted) {
        this.token = token;
        this.electionsStarted = electionsStarted;
    }

    public UUID getToken() {
        return token;
    }

    public String isElectionsStarted() {
        return electionsStarted;
    }

    public void validate() throws ElectionsException {
        if (this.token == null || this.token.toString().isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
    }
}

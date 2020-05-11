package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;

import java.util.UUID;

public class VoteForCandidateDtoRequest {
    private String candidateFullName;
    private UUID token;

    public VoteForCandidateDtoRequest(String candidateFullName, UUID token) {
        this.candidateFullName = candidateFullName;
        this.token = token;
    }

    public String getCandidateFullName() {
        return candidateFullName;
    }

    public UUID getToken() {
        return token;
    }

    public void validate() throws ElectionsException {
        if (this.token == null || this.token.toString().isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_TOKEN);
        }
    }
}

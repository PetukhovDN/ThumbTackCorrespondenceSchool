package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Candidate;

import java.util.UUID;

public class VoteForCandidateDtoRequest {
    private Candidate candidate;
    private UUID token;

    public VoteForCandidateDtoRequest(Candidate candidate, UUID token) {
        this.candidate = candidate;
        this.token = token;
    }

    public Candidate getCandidate() {
        return candidate;
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

package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.Candidate;

public class ChooseMajorDtoResponse {
    private Candidate candidate;

    public ChooseMajorDtoResponse(Candidate candidate) {
        this.candidate = candidate;
    }

    public Candidate getCandidate() {
        return candidate;
    }
}

package net.thumbtack.school.elections.dto.response;

public class ChooseMajorResponse {
    private String candidateFullName;

    public ChooseMajorResponse(String candidate) {
        this.candidateFullName = candidate;
    }

    public String getCandidateFullName() {
        return candidateFullName;
    }
}

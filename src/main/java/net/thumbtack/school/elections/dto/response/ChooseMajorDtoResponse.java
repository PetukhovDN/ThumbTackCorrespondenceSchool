package net.thumbtack.school.elections.dto.response;

public class ChooseMajorDtoResponse {
    private String candidateFullName;

    public ChooseMajorDtoResponse(String candidate) {
        this.candidateFullName = candidate;
    }

    public String getCandidateFullName() {
        return candidateFullName;
    }
}

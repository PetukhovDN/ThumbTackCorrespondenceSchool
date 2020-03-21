package net.thumbtack.school.elections.dto.response.candidateResponses;

import net.thumbtack.school.elections.model.Candidate;

import java.util.List;

public class GetAllAgreedCandidatesDtoResponse {
    private List<Candidate> candidateList;

    public GetAllAgreedCandidatesDtoResponse(List<Candidate> candidateList) {
        this.candidateList = candidateList;
    }

    public List<Candidate> getCandidateList() {
        return candidateList;
    }
}

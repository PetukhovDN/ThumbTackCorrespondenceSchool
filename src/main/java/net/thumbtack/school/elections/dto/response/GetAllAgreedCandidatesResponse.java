package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.CandidateProgram;

import java.util.Map;

public class GetAllAgreedCandidatesResponse {
    private Map<Candidate, CandidateProgram> candidateList;

    public GetAllAgreedCandidatesResponse(Map<Candidate, CandidateProgram> candidateList) {
        this.candidateList = candidateList;
    }

    public Map<Candidate, CandidateProgram> getCandidateList() {
        return candidateList;
    }
}

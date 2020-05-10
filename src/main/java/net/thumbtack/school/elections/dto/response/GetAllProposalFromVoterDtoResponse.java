package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.CandidateProgram;

import java.util.Map;

public class GetAllProposalFromVoterDtoResponse {
    private Map<String, CandidateProgram> proposalVoterMap;


    public GetAllProposalFromVoterDtoResponse(Map<String, CandidateProgram> proposalVoterMap) {
        this.proposalVoterMap = proposalVoterMap;
    }

    public Map<String, CandidateProgram> getProposalVoterMap() {
        return proposalVoterMap;
    }
}

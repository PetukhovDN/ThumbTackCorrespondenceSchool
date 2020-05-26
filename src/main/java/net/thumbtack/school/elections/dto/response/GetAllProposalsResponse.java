package net.thumbtack.school.elections.dto.response;

import java.util.Map;

public class GetAllProposalsResponse {
    private Map<String, Double> proposalList;

    public GetAllProposalsResponse(Map<String, Double> proposalList) {
        this.proposalList = proposalList;
    }

    public Map<String, Double> getProposalList() {
        return proposalList;
    }
}

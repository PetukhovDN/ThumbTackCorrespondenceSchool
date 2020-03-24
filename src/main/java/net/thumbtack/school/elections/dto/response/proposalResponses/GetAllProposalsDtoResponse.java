package net.thumbtack.school.elections.dto.response.proposalResponses;

import java.util.Map;

public class GetAllProposalsDtoResponse {
    private Map<String, Double> proposalList;

    public GetAllProposalsDtoResponse(Map<String, Double> proposalList) {
        this.proposalList = proposalList;
    }

    public Map<String, Double> getProposalList() {
        return proposalList;
    }
}

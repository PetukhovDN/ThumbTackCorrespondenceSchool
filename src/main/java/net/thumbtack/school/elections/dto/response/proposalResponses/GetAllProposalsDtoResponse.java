package net.thumbtack.school.elections.dto.response.proposalResponses;

import java.util.Map;

public class GetAllProposalsDtoResponse {
    private Map<String, Integer> proposalList;

    public GetAllProposalsDtoResponse(Map<String, Integer> proposalList) {
        this.proposalList = proposalList;
    }

    public Map<String, Integer> getProposalList() {
        return proposalList;
    }
}

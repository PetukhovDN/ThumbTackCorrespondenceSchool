package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.enums.ResultsOfRequests;

public class RemoveProposalResponse {
    private ResultsOfRequests result;

    public RemoveProposalResponse(ResultsOfRequests result) {
        this.result = result;
    }

    public ResultsOfRequests getResult() {
        return result;
    }
}

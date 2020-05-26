package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.enums.ResultsOfRequests;

public class AddCandidateResponse {
    private ResultsOfRequests result;

    public AddCandidateResponse(ResultsOfRequests result) {
        this.result = result;
    }

    public ResultsOfRequests getResult() {
        return result;
    }
}

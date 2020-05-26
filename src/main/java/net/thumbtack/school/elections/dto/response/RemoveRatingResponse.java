package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.enums.ResultsOfRequests;

public class RemoveRatingResponse {
    private ResultsOfRequests result;

    public RemoveRatingResponse(ResultsOfRequests result) {
        this.result = result;
    }

    public ResultsOfRequests getResult() {
        return result;
    }
}

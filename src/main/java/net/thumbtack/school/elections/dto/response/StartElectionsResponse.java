package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.enums.ResultsOfRequests;

public class StartElectionsResponse {
    private ResultsOfRequests result;

    public StartElectionsResponse(ResultsOfRequests result) {
        this.result = result;
    }

    public ResultsOfRequests getResult() {
        return result;
    }
}

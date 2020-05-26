package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.enums.ResultsOfRequests;

public class LogoutVoterResponse {
    private ResultsOfRequests result;

    public LogoutVoterResponse(ResultsOfRequests result) {
        this.result = result;
    }

    public ResultsOfRequests getResult() {
        return result;
    }
}

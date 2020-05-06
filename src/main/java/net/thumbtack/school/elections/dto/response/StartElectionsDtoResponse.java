package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class StartElectionsDtoResponse {
    private UUID token;

    public StartElectionsDtoResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

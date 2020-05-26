package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class StartElectionsResponse {
    private UUID token;

    public StartElectionsResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

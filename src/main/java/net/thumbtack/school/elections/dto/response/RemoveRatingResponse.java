package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class RemoveRatingResponse {
    private UUID token;

    public RemoveRatingResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

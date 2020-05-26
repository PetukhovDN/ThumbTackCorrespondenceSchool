package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class AddRatingResponse {
    private UUID token;

    public AddRatingResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

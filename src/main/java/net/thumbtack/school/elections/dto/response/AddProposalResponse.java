package net.thumbtack.school.elections.dto.response;

import java.util.UUID;

public class AddProposalResponse {
    private UUID token; // REVU Много ответов с токеном. Для чего постоянно возвращать токен клиенту?

    public AddProposalResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }
}

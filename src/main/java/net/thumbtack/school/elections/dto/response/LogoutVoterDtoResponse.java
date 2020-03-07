package net.thumbtack.school.elections.dto.response;

public class LogoutVoterDtoResponse {
    private String success;

    public LogoutVoterDtoResponse(Boolean success) {
        if (success) {
            this.success = "Выход прошел успешно";
        }
    }

    public String getToken() {
        return success;
    }
}

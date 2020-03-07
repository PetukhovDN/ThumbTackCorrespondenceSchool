package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.exceptions.VoterException;

public class LoginVoterDtoRequest {

    private String login;
    private String password;

    public LoginVoterDtoRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void validate() throws VoterException {
        if (this.login == null || this.login.isEmpty()) {
            throw new VoterException(ExceptionErrorCode.EMPTY_VOTER_LOGIN);
        }
        if (this.password == null || this.password.isEmpty()) {
            throw new VoterException(ExceptionErrorCode.EMPTY_VOTER_PASSWORD);
        }
    }
}

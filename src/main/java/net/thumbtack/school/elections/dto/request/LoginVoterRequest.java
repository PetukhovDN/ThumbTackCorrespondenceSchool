package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorInfo;

public class LoginVoterRequest {

    private String login;
    private String password;

    public LoginVoterRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void validate() throws ElectionsException {
        if (this.login == null || this.login.isEmpty()) {
            throw new ElectionsException(ExceptionErrorInfo.EMPTY_VOTER_LOGIN);
        }
        if (this.password == null || this.password.isEmpty()) {
            throw new ElectionsException(ExceptionErrorInfo.EMPTY_VOTER_PASSWORD);
        }
    }
}

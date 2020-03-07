package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.LoginVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.LoginVoterDtoResponse;
import net.thumbtack.school.elections.exceptions.VoterException;

public class LoginVoterService {
    private String requestJsonString;

    public LoginVoterService(String requestJsonString) {
        this.requestJsonString = requestJsonString;
    }

    public String validateAndLogin() {
        String result;
        try {
            LoginVoterDtoRequest loginVoterDtoRequest = new Gson().fromJson(requestJsonString, LoginVoterDtoRequest.class);
            loginVoterDtoRequest.validate();
            VoterDaoImpl voterDao = new VoterDaoImpl();
            LoginVoterDtoResponse loginVoterDtoResponse = new LoginVoterDtoResponse(voterDao.loginToDatabase(loginVoterDtoRequest.getLogin(), loginVoterDtoRequest.getPassword()));
            result = new Gson().toJson(loginVoterDtoResponse);
        } catch (VoterException e) {
            result = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return result;
    }
}

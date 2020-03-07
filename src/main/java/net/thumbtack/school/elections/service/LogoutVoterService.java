package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.LogoutVoterDtoResponse;
import net.thumbtack.school.elections.exceptions.VoterException;

public class LogoutVoterService {

    private String requestJsonString;

    public LogoutVoterService(String requestJsonString) {
        this.requestJsonString = requestJsonString;
    }

    public String validateAndLogout() {
        String result;
        try {
            LogoutVoterDtoRequest logoutVoterDtoRequest = new Gson().fromJson(requestJsonString, LogoutVoterDtoRequest.class);
            logoutVoterDtoRequest.validate();
            VoterDaoImpl voterDao = new VoterDaoImpl();
            LogoutVoterDtoResponse logoutVoterDtoResponse = new LogoutVoterDtoResponse(voterDao.logoutFromDatabase(logoutVoterDtoRequest.getToken()));
            result = new Gson().toJson(logoutVoterDtoResponse);
        } catch (VoterException e) {
            result = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return result;
    }
}

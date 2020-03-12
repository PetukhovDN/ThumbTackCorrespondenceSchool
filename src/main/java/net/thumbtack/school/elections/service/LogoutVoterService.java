package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.LogoutVoterDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;

public class LogoutVoterService {

    private String requestJsonString;

    public LogoutVoterService(String requestJsonString) {
        this.requestJsonString = requestJsonString;
    }

    public String logoutIfValid() {
        String result;
        try {
            LogoutVoterDtoRequest logoutVDR = new Gson().fromJson(requestJsonString, LogoutVoterDtoRequest.class);
            logoutVDR.validate();
            VoterDaoImpl voterDao = new VoterDaoImpl();
            LogoutVoterDtoResponse logoutVoterDtoResponse = new LogoutVoterDtoResponse(voterDao.logoutFromDatabase(logoutVDR.getToken()));
            result = new Gson().toJson(logoutVoterDtoResponse);
        } catch (ElectionsException e) {
            result = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return result;
    }
}

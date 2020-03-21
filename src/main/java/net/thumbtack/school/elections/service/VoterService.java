package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.voterRequests.GetAllVotersDtoRequest;
import net.thumbtack.school.elections.dto.request.voterRequests.LoginVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.voterRequests.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.voterRequests.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.voterResponses.GetAllVotersDtoResponse;
import net.thumbtack.school.elections.dto.response.voterResponses.LoginVoterDtoResponse;
import net.thumbtack.school.elections.dto.response.voterResponses.LogoutVoterDtoResponse;
import net.thumbtack.school.elections.dto.response.voterResponses.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Voter;

public class VoterService {
    private String requestJsonString;
    private String resultJsonString;
    private VoterDaoImpl voterDao = new VoterDaoImpl();

    public VoterService(String requestJsonString) {
        this.requestJsonString = requestJsonString;
    }

    public String registerVoter() {
        try {
            RegisterVoterDtoRequest registerRequest = new Gson().fromJson(requestJsonString, RegisterVoterDtoRequest.class);
            registerRequest.validate();
            Voter voter = new Voter(registerRequest);
            RegisterVoterDtoResponse registerResponse = new RegisterVoterDtoResponse(voterDao.insertToDataBase(voter));
            resultJsonString = new Gson().toJson(registerResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String loginVoter() {
        try {
            LoginVoterDtoRequest loginRequest = new Gson().fromJson(requestJsonString, LoginVoterDtoRequest.class);
            loginRequest.validate();
            LoginVoterDtoResponse loginResponse = new LoginVoterDtoResponse(voterDao.loginToDatabase(loginRequest.getLogin(), loginRequest.getPassword()));
            resultJsonString = new Gson().toJson(loginResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String logoutVoter() {
        try {
            LogoutVoterDtoRequest logoutRequest = new Gson().fromJson(requestJsonString, LogoutVoterDtoRequest.class);
            logoutRequest.validate();
            LogoutVoterDtoResponse logoutResponse = new LogoutVoterDtoResponse(voterDao.logoutFromDatabase(logoutRequest.getToken()));
            resultJsonString = new Gson().toJson(logoutResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String getAllVoters() {
        try {
            GetAllVotersDtoRequest getVotersRequest = new Gson().fromJson(requestJsonString, GetAllVotersDtoRequest.class);
            getVotersRequest.validate();
            GetAllVotersDtoResponse getVotersResponse = new GetAllVotersDtoResponse(voterDao.getAllVotersFromDatabase(getVotersRequest.getToken()));
            resultJsonString = new Gson().toJson(getVotersResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }
}

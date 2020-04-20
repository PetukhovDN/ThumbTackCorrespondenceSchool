package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.GetAllVotersDtoRequest;
import net.thumbtack.school.elections.dto.request.LoginVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.GetAllVotersDtoResponse;
import net.thumbtack.school.elections.dto.response.LoginVoterDtoResponse;
import net.thumbtack.school.elections.dto.response.LogoutVoterDtoResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Voter;

import static net.thumbtack.school.elections.server.Server.gson;

public class VoterService {
    private String resultJsonString;
    private VoterDaoImpl voterDao = new VoterDaoImpl();

    public String registerVoter(String requestJsonString) {
        try {
            RegisterVoterDtoRequest registerRequest = gson.fromJson(requestJsonString, RegisterVoterDtoRequest.class);
            registerRequest.validate();
            Voter voter = new Voter(registerRequest);
            RegisterVoterDtoResponse registerResponse = new RegisterVoterDtoResponse(voterDao.insertToDataBase(voter));
            resultJsonString = gson.toJson(registerResponse);
        } catch (ElectionsException e) {
            resultJsonString = gson.toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String loginVoter(String requestJsonString) {
        try {
            LoginVoterDtoRequest loginRequest = gson.fromJson(requestJsonString, LoginVoterDtoRequest.class);
            loginRequest.validate();
            LoginVoterDtoResponse loginResponse = new LoginVoterDtoResponse(voterDao.loginToDatabase(loginRequest.getLogin(), loginRequest.getPassword()));
            resultJsonString = gson.toJson(loginResponse);
        } catch (ElectionsException e) {
            resultJsonString = gson.toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String logoutVoter(String requestJsonString) {
        try {
            LogoutVoterDtoRequest logoutRequest = gson.fromJson(requestJsonString, LogoutVoterDtoRequest.class);
            logoutRequest.validate();
            LogoutVoterDtoResponse logoutResponse = new LogoutVoterDtoResponse(voterDao.logoutFromDatabase(logoutRequest.getToken()));
            resultJsonString = gson.toJson(logoutResponse);
        } catch (ElectionsException e) {
            resultJsonString = gson.toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String getAllVoters(String requestJsonString) {
        try {
            GetAllVotersDtoRequest getVotersRequest = gson.fromJson(requestJsonString, GetAllVotersDtoRequest.class);
            getVotersRequest.validate();
            GetAllVotersDtoResponse getVotersResponse = new GetAllVotersDtoResponse(voterDao.getAllVotersFromDatabase(getVotersRequest.getToken()));
            resultJsonString = gson.toJson(getVotersResponse);
        } catch (ElectionsException e) {
            resultJsonString = gson.toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }
}

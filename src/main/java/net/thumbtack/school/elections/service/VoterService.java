package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.GetAllVotersRequest;
import net.thumbtack.school.elections.dto.request.LoginVoterRequest;
import net.thumbtack.school.elections.dto.request.LogoutVoterRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterRequest;
import net.thumbtack.school.elections.dto.response.GetAllVotersResponse;
import net.thumbtack.school.elections.dto.response.LoginVoterResponse;
import net.thumbtack.school.elections.dto.response.LogoutVoterResponse;
import net.thumbtack.school.elections.dto.response.RegisterVoterResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Voter;

import static net.thumbtack.school.elections.server.Server.gson;

public class VoterService {
    private final VoterDao voterDao;

    public VoterService() {
        voterDao = new VoterDaoImpl();
    }

    public String registerVoter(String requestJsonString) {
        try {
            RegisterVoterRequest registerRequest = gson.fromJson(requestJsonString, RegisterVoterRequest.class);
            registerRequest.validate();
            Voter voter = new Voter(registerRequest);
            RegisterVoterResponse registerResponse = new RegisterVoterResponse(voterDao.insertToDataBase(voter));
            return gson.toJson(registerResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString()); // REVU Обратите внимание на раздел "Обработка ошибок" в документе "Рекомендации к заданию 11".
        }
    }

    public String logoutVoter(String requestJsonString) {
        try {
            LogoutVoterRequest logoutRequest = gson.fromJson(requestJsonString, LogoutVoterRequest.class);
            logoutRequest.validate();
            LogoutVoterResponse logoutResponse = new LogoutVoterResponse(voterDao.logoutFromDatabase(logoutRequest.getToken()));
            return gson.toJson(logoutResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String loginVoter(String requestJsonString) {
        try {
            LoginVoterRequest loginRequest = gson.fromJson(requestJsonString, LoginVoterRequest.class);
            loginRequest.validate();
            LoginVoterResponse loginResponse = new LoginVoterResponse(voterDao.loginToDatabase(loginRequest.getLogin(), loginRequest.getPassword()));
            return gson.toJson(loginResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String getAllVoters(String requestJsonString) {
        try {
            GetAllVotersRequest getVotersRequest = gson.fromJson(requestJsonString, GetAllVotersRequest.class);
            getVotersRequest.validate();
            GetAllVotersResponse getVotersResponse = new GetAllVotersResponse(voterDao.getAllVotersFromDatabase(getVotersRequest.getToken()));
            return gson.toJson(getVotersResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }
}

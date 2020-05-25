package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.dao.VoterDao;
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
    private final VoterDao voterDao;

    public VoterService() {
        voterDao = new VoterDaoImpl();
    }

    public String registerVoter(String requestJsonString) {
        try {
            RegisterVoterDtoRequest registerRequest = gson.fromJson(requestJsonString, RegisterVoterDtoRequest.class);
            registerRequest.validate();
            RegisterVoterDtoResponse registerResponse = new RegisterVoterDtoResponse(voterDao.insertToDataBase(new Voter(registerRequest))); // REVU Не пытайтесь поместить в одну строчку максимум действий. Это тяжело читать и неудобно отлаживать.
            return gson.toJson(registerResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString()); // REVU Обратите внимание на раздел "Обработка ошибок" в документе "Рекомендации к заданию 11".
        }
    }

    public String logoutVoter(String requestJsonString) {
        try {
            LogoutVoterDtoRequest logoutRequest = gson.fromJson(requestJsonString, LogoutVoterDtoRequest.class);
            logoutRequest.validate();
            LogoutVoterDtoResponse logoutResponse = new LogoutVoterDtoResponse(voterDao.logoutFromDatabase(logoutRequest.getToken()));
            return gson.toJson(logoutResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String loginVoter(String requestJsonString) {
        try {
            LoginVoterDtoRequest loginRequest = gson.fromJson(requestJsonString, LoginVoterDtoRequest.class);
            loginRequest.validate();
            LoginVoterDtoResponse loginResponse = new LoginVoterDtoResponse(voterDao.loginToDatabase(loginRequest.getLogin(), loginRequest.getPassword()));
            return gson.toJson(loginResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String getAllVoters(String requestJsonString) {
        try {
            GetAllVotersDtoRequest getVotersRequest = gson.fromJson(requestJsonString, GetAllVotersDtoRequest.class);
            getVotersRequest.validate();
            GetAllVotersDtoResponse getVotersResponse = new GetAllVotersDtoResponse(voterDao.getAllVotersFromDatabase(getVotersRequest.getToken()));
            return gson.toJson(getVotersResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }
}

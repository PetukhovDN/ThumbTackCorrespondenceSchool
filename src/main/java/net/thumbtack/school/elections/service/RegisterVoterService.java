package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.exceptions.VoterException;
import net.thumbtack.school.elections.model.Voter;

public class RegisterVoterService {
    private String requestJsonString;

    public RegisterVoterService(String requestJsonString) {
        this.requestJsonString = requestJsonString;
    }

    public String validateAndCreate() {
        String result;
        try {
            RegisterVoterDtoRequest rvdr = new Gson().fromJson(requestJsonString, RegisterVoterDtoRequest.class);
            rvdr.validate();
            VoterDaoImpl voterDao = new VoterDaoImpl();
            Voter voter = new Voter(rvdr);
            RegisterVoterDtoResponse registerVoterDtoResponse = new RegisterVoterDtoResponse(voterDao.insertToDataBase(voter));
            result = new Gson().toJson(registerVoterDtoResponse);
        } catch (VoterException e) {
            result = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return result;
    }


}

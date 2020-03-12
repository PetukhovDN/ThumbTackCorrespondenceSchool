package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.GetAllVotersRequest;
import net.thumbtack.school.elections.dto.response.GetAllVotersResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;

public class GetAllVotersService {
    private String requestJsonString;

    public GetAllVotersService(String requestJsonString) {
        this.requestJsonString = requestJsonString;
    }

    public String returnIfValid() {
        String result;
        try {
            GetAllVotersRequest getAllVotersRequest = new Gson().fromJson(requestJsonString, GetAllVotersRequest.class);
            getAllVotersRequest.validate();
            VoterDaoImpl voterDao = new VoterDaoImpl();
            GetAllVotersResponse getAllVotersResponse = new GetAllVotersResponse(voterDao.getAllVoters(getAllVotersRequest.getToken()));
            result = new Gson().toJson(getAllVotersResponse);
        } catch (ElectionsException e) {
            result = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return result;
    }
}

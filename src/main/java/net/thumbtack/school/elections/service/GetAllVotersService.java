package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.dto.request.GetAllVotersDtoRequest;
import net.thumbtack.school.elections.dto.response.GetAllVotersDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;

public class GetAllVotersService {
    private String requestJsonString;

    public GetAllVotersService(String requestJsonString) {
        this.requestJsonString = requestJsonString;
    }

    public String returnIfValid() {
        String result;
        try {
            GetAllVotersDtoRequest getAllVotersDtoRequest = new Gson().fromJson(requestJsonString, GetAllVotersDtoRequest.class);
            getAllVotersDtoRequest.validate();
            VoterDaoImpl voterDao = new VoterDaoImpl();
            GetAllVotersDtoResponse getAllVotersDtoResponse = new GetAllVotersDtoResponse(voterDao.getAllVotersFromDatabase(getAllVotersDtoRequest.getToken()));
            result = new Gson().toJson(getAllVotersDtoResponse);
        } catch (ElectionsException e) {
            result = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return result;
    }
}

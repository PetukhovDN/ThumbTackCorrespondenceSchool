package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.dto.request.AddCandidateDtoRequest;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Candidate;

public class CandidateService {
    private String requestJsonString;
    private String resultJsonString;
    private CandidateDaoImpl candidateDao = new CandidateDaoImpl();

    public CandidateService(String requestJsonString) {
        this.requestJsonString = requestJsonString;
    }

    public String addCandidate() {
        try {
            AddCandidateDtoRequest addCandidateRequest = new Gson().fromJson(requestJsonString, AddCandidateDtoRequest.class);
            addCandidateRequest.validate();
            Candidate candidate = new Candidate(addCandidateRequest);
            RegisterVoterDtoResponse registerResponse = new RegisterVoterDtoResponse(candidateDao.addCandidateToDatabase(candidate, addCandidateRequest.getToken()));
            resultJsonString = new Gson().toJson(registerResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String agreeCandidate(){

        return resultJsonString;
    }

    public String getAllCandidates() {

        return resultJsonString;
    }
}

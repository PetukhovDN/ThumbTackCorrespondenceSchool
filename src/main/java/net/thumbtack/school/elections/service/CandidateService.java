package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.dto.request.candidateRequests.AddCandidateDtoRequest;
import net.thumbtack.school.elections.dto.request.candidateRequests.AgreeToBeCandidateDtoRequest;
import net.thumbtack.school.elections.dto.request.candidateRequests.GetAllAgreedCandidatesDtoRequest;
import net.thumbtack.school.elections.dto.response.candidateResponses.AddCandidateDtoResponse;
import net.thumbtack.school.elections.dto.response.candidateResponses.AgreeToBeCandidateDtoResponse;
import net.thumbtack.school.elections.dto.response.candidateResponses.GetAllAgreedCandidatesDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Candidate;

public class CandidateService {
    private String requestJsonString;
    private String resultJsonString;

    public CandidateService(String requestJsonString) {
        this.requestJsonString = requestJsonString;
    }

    public String addCandidate() {
        try {
            AddCandidateDtoRequest addCandidateRequest = new Gson().fromJson(requestJsonString, AddCandidateDtoRequest.class);
            addCandidateRequest.validate();
            Candidate candidate = new Candidate(addCandidateRequest);
            AddCandidateDtoResponse addCandidateResponse = new AddCandidateDtoResponse(new CandidateDaoImpl().addCandidateToDatabase(candidate, addCandidateRequest.getToken()));
            resultJsonString = new Gson().toJson(addCandidateResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String agreeToBeCandidate() {
        try {
            AgreeToBeCandidateDtoRequest agreeRequest = new Gson().fromJson(requestJsonString, AgreeToBeCandidateDtoRequest.class);
            agreeRequest.validate();
            AgreeToBeCandidateDtoResponse agreeResponse = new AgreeToBeCandidateDtoResponse(new CandidateDaoImpl().agreeToBeCandidate(agreeRequest.getToken()));
            resultJsonString = new Gson().toJson(agreeResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String getAllAgreedCandidates() {
        try {
            GetAllAgreedCandidatesDtoRequest allCandidatesRequest = new Gson().fromJson(requestJsonString, GetAllAgreedCandidatesDtoRequest.class);
            allCandidatesRequest.validate();
            GetAllAgreedCandidatesDtoResponse allCandidatesResponse = new GetAllAgreedCandidatesDtoResponse(new CandidateDaoImpl().getAllAgreedCandidates(allCandidatesRequest.getToken()));
            resultJsonString = new Gson().toJson(allCandidatesResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }
}

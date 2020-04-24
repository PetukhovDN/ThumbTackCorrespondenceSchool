package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.dto.request.AddCandidateDtoRequest;
import net.thumbtack.school.elections.dto.request.AgreeToBeCandidateDtoRequest;
import net.thumbtack.school.elections.dto.request.GetAllAgreedCandidatesDtoRequest;
import net.thumbtack.school.elections.dto.response.AddCandidateDtoResponse;
import net.thumbtack.school.elections.dto.response.AgreeToBeCandidateDtoResponse;
import net.thumbtack.school.elections.dto.response.GetAllAgreedCandidatesDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Candidate;

import static net.thumbtack.school.elections.server.Server.gson;

public class CandidateService {

    public String addCandidate(String requestJsonString) {
        try {
            AddCandidateDtoRequest addCandidateRequest = gson.fromJson(requestJsonString, AddCandidateDtoRequest.class);
            addCandidateRequest.validate();
            Candidate candidate = new Candidate(addCandidateRequest);
            AddCandidateDtoResponse addCandidateResponse = new AddCandidateDtoResponse(new CandidateDaoImpl().addCandidateToDatabase(candidate, addCandidateRequest.getToken()));
            return gson.toJson(addCandidateResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String agreeToBeCandidate(String requestJsonString) {
        try {
            AgreeToBeCandidateDtoRequest agreeRequest = gson.fromJson(requestJsonString, AgreeToBeCandidateDtoRequest.class);
            agreeRequest.validate();
            AgreeToBeCandidateDtoResponse agreeResponse = new AgreeToBeCandidateDtoResponse(new CandidateDaoImpl().agreeToBeCandidate(agreeRequest.getToken()));
            return gson.toJson(agreeResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String getAllAgreedCandidates(String requestJsonString) {
        try {
            GetAllAgreedCandidatesDtoRequest allCandidatesRequest = gson.fromJson(requestJsonString, GetAllAgreedCandidatesDtoRequest.class);
            allCandidatesRequest.validate();
            GetAllAgreedCandidatesDtoResponse allCandidatesResponse = new GetAllAgreedCandidatesDtoResponse(new CandidateDaoImpl().getAllAgreedCandidates(allCandidatesRequest.getToken()));
            return gson.toJson(allCandidatesResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }
}

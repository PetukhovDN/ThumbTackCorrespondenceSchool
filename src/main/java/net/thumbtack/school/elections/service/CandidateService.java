package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.dto.request.*;
import net.thumbtack.school.elections.dto.response.*;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Candidate;

import static net.thumbtack.school.elections.server.Server.gson;

public class CandidateService {
    private final CandidateDao candidateDao;

    public CandidateService() {
        candidateDao = new CandidateDaoImpl();
    }

    public String addCandidate(String requestJsonString) {
        try {
            AddCandidateDtoRequest addCandidateRequest = gson.fromJson(requestJsonString, AddCandidateDtoRequest.class);
            addCandidateRequest.validate();
            Candidate candidate = new Candidate(addCandidateRequest);
            AddCandidateDtoResponse addCandidateResponse = new AddCandidateDtoResponse(candidateDao.addCandidateToDatabase(candidate, addCandidateRequest.getToken()));
            return gson.toJson(addCandidateResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String agreeToBeCandidate(String requestJsonString) {
        try {
            AgreeToBeCandidateDtoRequest agreeRequest = gson.fromJson(requestJsonString, AgreeToBeCandidateDtoRequest.class);
            agreeRequest.validate();
            AgreeToBeCandidateDtoResponse agreeResponse = new AgreeToBeCandidateDtoResponse(candidateDao.agreeToBeCandidate(agreeRequest.getToken()));
            return gson.toJson(agreeResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String addProposalToCandidateProgram(String requestJsonString) {
        try {
            AddProposalToCandidateProgramDtoRequest addRequest = gson.fromJson(requestJsonString, AddProposalToCandidateProgramDtoRequest.class);
            addRequest.validate();
            AddProposalToCandidateProgramDtoResponse addResponse = new AddProposalToCandidateProgramDtoResponse(candidateDao.addProposalToCandidateProgram(addRequest.getProposal(), addRequest.getToken()));
            return gson.toJson(addResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String removeProposalFromCandidateProgram(String requestJsonString) {
        try {
            RemoveProposalFromCandidateProgramDtoRequest removeRequest = gson.fromJson(requestJsonString, RemoveProposalFromCandidateProgramDtoRequest.class);
            removeRequest.validate();
            RemoveProposalFromCandidateProgramDtoResponse removeResponse = new RemoveProposalFromCandidateProgramDtoResponse(candidateDao.removeProposalFromCandidateProgram(removeRequest.getProposal(), removeRequest.getToken())); // REVU Очень длинные строки
            return gson.toJson(removeResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String getAllAgreedCandidates(String requestJsonString) {
        try {
            GetAllAgreedCandidatesDtoRequest allCandidatesRequest = gson.fromJson(requestJsonString, GetAllAgreedCandidatesDtoRequest.class);
            allCandidatesRequest.validate();
            GetAllAgreedCandidatesDtoResponse allCandidatesResponse = new GetAllAgreedCandidatesDtoResponse(candidateDao.getAllAgreedCandidates(allCandidatesRequest.getToken()));
            return gson.toJson(allCandidatesResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }
}

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
            AddCandidateRequest addCandidateRequest = gson.fromJson(requestJsonString, AddCandidateRequest.class);
            addCandidateRequest.validate();
            Candidate candidate = new Candidate(addCandidateRequest);
            AddCandidateResponse addCandidateResponse = new AddCandidateResponse(candidateDao.addCandidateToDatabase(candidate, addCandidateRequest.getToken()));
            return gson.toJson(addCandidateResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getExceptionErrorInfo().getErrorCode() + "\n" + e.getExceptionErrorInfo().getErrorString());
        }
    }

    public String agreeToBeCandidate(String requestJsonString) {
        try {
            AgreeToBeCandidateRequest agreeRequest = gson.fromJson(requestJsonString, AgreeToBeCandidateRequest.class);
            agreeRequest.validate();
            AgreeToBeCandidateResponse agreeResponse = new AgreeToBeCandidateResponse(candidateDao.agreeToBeCandidate(agreeRequest.getToken()));
            return gson.toJson(agreeResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getExceptionErrorInfo().getErrorCode() + "\n" + e.getExceptionErrorInfo().getErrorString());
        }
    }

    public String addProposalToCandidateProgram(String requestJsonString) {
        try {
            AddProposalRequest addRequest = gson.fromJson(requestJsonString, AddProposalRequest.class);
            addRequest.validate();
            AddProposalResponse addResponse = new AddProposalResponse(candidateDao.addProposalToCandidateProgram(addRequest.getProposal(), addRequest.getToken()));
            return gson.toJson(addResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getExceptionErrorInfo().getErrorCode() + "\n" + e.getExceptionErrorInfo().getErrorString());
        }
    }

    public String removeProposalFromCandidateProgram(String requestJsonString) {
        try {
            RemoveProposalRequest removeRequest = gson.fromJson(requestJsonString, RemoveProposalRequest.class);
            removeRequest.validate();
            RemoveProposalResponse removeResponse = new RemoveProposalResponse(candidateDao.removeProposal(removeRequest.getProposal(), removeRequest.getToken()));
            return gson.toJson(removeResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getExceptionErrorInfo().getErrorCode() + "\n" + e.getExceptionErrorInfo().getErrorString());
        }
    }

    public String getAllAgreedCandidates(String requestJsonString) {
        try {
            GetAllAgreedCandidatesRequest allCandidatesRequest = gson.fromJson(requestJsonString, GetAllAgreedCandidatesRequest.class);
            allCandidatesRequest.validate();
            GetAllAgreedCandidatesResponse allCandidatesResponse = new GetAllAgreedCandidatesResponse(candidateDao.getAllAgreedCandidates(allCandidatesRequest.getToken()));
            return gson.toJson(allCandidatesResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getExceptionErrorInfo().getErrorCode() + "\n" + e.getExceptionErrorInfo().getErrorString());
        }
    }
}

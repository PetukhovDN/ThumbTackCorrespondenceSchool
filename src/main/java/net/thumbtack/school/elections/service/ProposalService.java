package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.dao.ProposalDao;
import net.thumbtack.school.elections.daoimpl.ProposalDaoImpl;
import net.thumbtack.school.elections.dto.request.*;
import net.thumbtack.school.elections.dto.response.*;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Proposal;

import static net.thumbtack.school.elections.server.Server.gson;

public class ProposalService {
    private final ProposalDao proposalDao;

    public ProposalService() {
        proposalDao = new ProposalDaoImpl();
    }

    public String makeProposal(String requestJsonString) {
        try {
            MakeProposalDtoRequest makeProposalRequest = gson.fromJson(requestJsonString, MakeProposalDtoRequest.class);
            makeProposalRequest.validate();
            Proposal proposal = new Proposal(makeProposalRequest);
            MakeProposalDtoResponse makeProposalResponse = new MakeProposalDtoResponse(proposalDao.makeProposal(proposal, makeProposalRequest.getToken()));
            return gson.toJson(makeProposalResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String addRatingForProposal(String requestJsonString) {
        try {
            AddRatingForProposalDtoRequest addRatingRequest = gson.fromJson(requestJsonString, AddRatingForProposalDtoRequest.class);
            addRatingRequest.validate();
            AddRatingForProposalDtoResponse addRatingResponse = new AddRatingForProposalDtoResponse(proposalDao.addRatingForProposal(addRatingRequest.getProposal(), addRatingRequest.getRating(), addRatingRequest.getToken()));
            return gson.toJson(addRatingResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String removeRatingFromProposal(String requestJsonString) {
        try {
            RemoveRatingFromProposalDtoRequest removeRatingRequest = gson.fromJson(requestJsonString, RemoveRatingFromProposalDtoRequest.class);
            removeRatingRequest.validate();
            RemoveRatingFromProposalDtoResponse removeRatingResponse = new RemoveRatingFromProposalDtoResponse(proposalDao.removeRatingFromProposal(removeRatingRequest.getProposal(), removeRatingRequest.getToken()));
            return gson.toJson(removeRatingResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String getAllProposalsWithRate(String requestJsonString) {
        try {
            GetAllProposalsDtoRequest getAllProposalsRequest = gson.fromJson(requestJsonString, GetAllProposalsDtoRequest.class);
            getAllProposalsRequest.validate();
            GetAllProposalsDtoResponse getAllProposalsResponse = new GetAllProposalsDtoResponse(proposalDao.getAllProposalsWithRate(getAllProposalsRequest.getToken()));
            return gson.toJson(getAllProposalsResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String getAllProposalsFromVoter(String requestJsonString) {
        try {
            GetAllProposalFromVoterDtoRequest getAllProposalsRequest = gson.fromJson(requestJsonString, GetAllProposalFromVoterDtoRequest.class);
            getAllProposalsRequest.validate();
            GetAllProposalFromVoterDtoResponse getAllProposalsResponse = new GetAllProposalFromVoterDtoResponse(proposalDao.getAllProposalsFromVoter(getAllProposalsRequest.getToken(), getAllProposalsRequest.getVotersFullNames()));
            return gson.toJson(getAllProposalsResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }
}

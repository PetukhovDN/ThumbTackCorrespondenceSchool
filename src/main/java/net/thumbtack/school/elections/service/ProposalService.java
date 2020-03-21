package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.ProposalDaoImpl;
import net.thumbtack.school.elections.dto.request.proposalRequests.AddRatingForProposalDtoRequest;
import net.thumbtack.school.elections.dto.request.proposalRequests.GetAllProposalsDtoRequest;
import net.thumbtack.school.elections.dto.request.proposalRequests.MakeProposalDtoRequest;
import net.thumbtack.school.elections.dto.request.proposalRequests.RemoveRatingFromProposalDtoRequest;
import net.thumbtack.school.elections.dto.response.proposalResponses.AddRatingForProposalDtoResponse;
import net.thumbtack.school.elections.dto.response.proposalResponses.GetAllProposalsDtoResponse;
import net.thumbtack.school.elections.dto.response.proposalResponses.MakeProposalDtoResponse;
import net.thumbtack.school.elections.dto.response.proposalResponses.RemoveRatingFromProposalDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Proposal;

public class ProposalService {
    private String requestJsonString;
    private String resultJsonString;
    private ProposalDaoImpl proposalDao = new ProposalDaoImpl();

    public ProposalService(String requestJsonString) {
        this.requestJsonString = requestJsonString;
    }

    public String makeProposal() {
        try {
            MakeProposalDtoRequest makeProposalRequest = new Gson().fromJson(requestJsonString, MakeProposalDtoRequest.class);
            makeProposalRequest.validate();
            Proposal proposal = new Proposal(makeProposalRequest);
            MakeProposalDtoResponse makeProposalResponse = new MakeProposalDtoResponse(proposalDao.makeProposal(proposal, makeProposalRequest.getToken()));
            resultJsonString = new Gson().toJson(makeProposalResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String addRatingForProposal() {
        try {
            AddRatingForProposalDtoRequest addRatingRequest = new Gson().fromJson(requestJsonString, AddRatingForProposalDtoRequest.class);
            addRatingRequest.validate();
            AddRatingForProposalDtoResponse addRatingResponse = new AddRatingForProposalDtoResponse(proposalDao.addRatingForProposal(addRatingRequest.getProposal(), addRatingRequest.getRating(), addRatingRequest.getToken()));
            resultJsonString = new Gson().toJson(addRatingResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String removeRatingFromProposal() {
        try {
            RemoveRatingFromProposalDtoRequest removeRatingRequest = new Gson().fromJson(requestJsonString, RemoveRatingFromProposalDtoRequest.class);
            removeRatingRequest.validate();
            RemoveRatingFromProposalDtoResponse removeRatingResponse = new RemoveRatingFromProposalDtoResponse(proposalDao.removeRatingFromProposal(removeRatingRequest.getProposal(), removeRatingRequest.getToken()));
            resultJsonString = new Gson().toJson(removeRatingResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String getAllProposals() {
        try {
            GetAllProposalsDtoRequest getAllProposalsRequest = new Gson().fromJson(requestJsonString, GetAllProposalsDtoRequest.class);
            getAllProposalsRequest.validate();
            GetAllProposalsDtoResponse getAllProposalsResponse = new GetAllProposalsDtoResponse(proposalDao.getAllProposals(getAllProposalsRequest.getToken()));
            resultJsonString = new Gson().toJson(getAllProposalsResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }
}

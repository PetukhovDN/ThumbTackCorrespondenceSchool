package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.daoimpl.ProposalDaoImpl;
import net.thumbtack.school.elections.dto.request.AddRatingForProposalDtoRequest;
import net.thumbtack.school.elections.dto.request.GetAllProposalsDtoRequest;
import net.thumbtack.school.elections.dto.request.MakeProposalDtoRequest;
import net.thumbtack.school.elections.dto.request.RemoveRatingFromProposalDtoRequest;
import net.thumbtack.school.elections.dto.response.AddRatingForProposalDtoResponse;
import net.thumbtack.school.elections.dto.response.GetAllProposalsDtoResponse;
import net.thumbtack.school.elections.dto.response.MakeProposalDtoResponse;
import net.thumbtack.school.elections.dto.response.RemoveRatingFromProposalDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Proposal;

import static net.thumbtack.school.elections.server.Server.gson;

public class ProposalService {
    private String resultJsonString;
    private ProposalDaoImpl proposalDao = new ProposalDaoImpl();


    public String makeProposal(String requestJsonString) {
        try {
            MakeProposalDtoRequest makeProposalRequest = gson.fromJson(requestJsonString, MakeProposalDtoRequest.class);
            makeProposalRequest.validate();
            Proposal proposal = new Proposal(makeProposalRequest);
            MakeProposalDtoResponse makeProposalResponse = new MakeProposalDtoResponse(proposalDao.makeProposal(proposal, makeProposalRequest.getToken()));
            resultJsonString = gson.toJson(makeProposalResponse);
        } catch (ElectionsException e) {
            resultJsonString = gson.toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String addRatingForProposal(String requestJsonString) {
        try {
            AddRatingForProposalDtoRequest addRatingRequest = gson.fromJson(requestJsonString, AddRatingForProposalDtoRequest.class);
            addRatingRequest.validate();
            AddRatingForProposalDtoResponse addRatingResponse = new AddRatingForProposalDtoResponse(proposalDao.addRatingForProposal(addRatingRequest.getProposal(), addRatingRequest.getRating(), addRatingRequest.getToken()));
            resultJsonString = gson.toJson(addRatingResponse);
        } catch (ElectionsException e) {
            resultJsonString = gson.toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String removeRatingFromProposal(String requestJsonString) {
        try {
            RemoveRatingFromProposalDtoRequest removeRatingRequest = gson.fromJson(requestJsonString, RemoveRatingFromProposalDtoRequest.class);
            removeRatingRequest.validate();
            RemoveRatingFromProposalDtoResponse removeRatingResponse = new RemoveRatingFromProposalDtoResponse(proposalDao.removeRatingFromProposal(removeRatingRequest.getProposal(), removeRatingRequest.getToken()));
            resultJsonString = gson.toJson(removeRatingResponse);
        } catch (ElectionsException e) {
            resultJsonString = gson.toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String getAllProposals(String requestJsonString) {
        try {
            GetAllProposalsDtoRequest getAllProposalsRequest = gson.fromJson(requestJsonString, GetAllProposalsDtoRequest.class);
            getAllProposalsRequest.validate();
            GetAllProposalsDtoResponse getAllProposalsResponse = new GetAllProposalsDtoResponse(proposalDao.getAllProposals(getAllProposalsRequest.getToken()));
            resultJsonString = gson.toJson(getAllProposalsResponse);
        } catch (ElectionsException e) {
            resultJsonString = gson.toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }
}

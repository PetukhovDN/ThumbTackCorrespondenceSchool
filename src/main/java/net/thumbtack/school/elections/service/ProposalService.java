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
            MakeProposalRequest makeProposalRequest = gson.fromJson(requestJsonString, MakeProposalRequest.class);
            makeProposalRequest.validate();
            Proposal proposal = new Proposal(makeProposalRequest);
            MakeProposalResponse makeProposalResponse = new MakeProposalResponse(proposalDao.makeProposal(proposal, makeProposalRequest.getToken()));
            return gson.toJson(makeProposalResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e);
        }
    }

    public String addRatingForProposal(String requestJsonString) {
        try {
            AddRatingRequest addRatingRequest = gson.fromJson(requestJsonString, AddRatingRequest.class);
            addRatingRequest.validate();
            AddRatingResponse addRatingResponse = new AddRatingResponse(proposalDao.addRating(addRatingRequest.getProposal(), addRatingRequest.getRating(), addRatingRequest.getToken()));
            return gson.toJson(addRatingResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e);
        }
    }

    public String removeRatingFromProposal(String requestJsonString) {
        try {
            RemoveRatingRequest removeRatingRequest = gson.fromJson(requestJsonString, RemoveRatingRequest.class);
            removeRatingRequest.validate();
            RemoveRatingResponse removeRatingResponse = new RemoveRatingResponse(proposalDao.removeRating(removeRatingRequest.getProposal(), removeRatingRequest.getToken()));
            return gson.toJson(removeRatingResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e);
        }
    }

    public String getAllProposalsWithRate(String requestJsonString) {
        try {
            GetAllProposalsRequest getAllProposalsRequest = gson.fromJson(requestJsonString, GetAllProposalsRequest.class);
            getAllProposalsRequest.validate();
            GetAllProposalsResponse getAllProposalsResponse = new GetAllProposalsResponse(proposalDao.getAllProposalsWithRate(getAllProposalsRequest.getToken()));
            return gson.toJson(getAllProposalsResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e);
        }
    }

    public String getAllProposalsFromVoter(String requestJsonString) {
        try {
            GetAllVoterProposalsRequest getAllProposalsRequest = gson.fromJson(requestJsonString, GetAllVoterProposalsRequest.class);
            getAllProposalsRequest.validate();
            GetAllVoterProposalsResponse getAllProposalsResponse = new GetAllVoterProposalsResponse(proposalDao.getAllProposalsFromVoter(getAllProposalsRequest.getToken(), getAllProposalsRequest.getVotersFullNames()));
            return gson.toJson(getAllProposalsResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e);
        }
    }
}

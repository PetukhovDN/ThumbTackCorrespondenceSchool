package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.dao.StartElectionsDao;
import net.thumbtack.school.elections.daoimpl.StartElectionsDaoImpl;
import net.thumbtack.school.elections.dto.request.ChooseMajorRequest;
import net.thumbtack.school.elections.dto.request.StartElectionsRequest;
import net.thumbtack.school.elections.dto.request.VoteForCandidateRequest;
import net.thumbtack.school.elections.dto.response.ChooseMajorResponse;
import net.thumbtack.school.elections.dto.response.StartElectionsResponse;
import net.thumbtack.school.elections.dto.response.VoteForCandidateResponse;
import net.thumbtack.school.elections.enums.ResultsOfRequests;
import net.thumbtack.school.elections.exceptions.ElectionsException;

import static net.thumbtack.school.elections.server.Server.gson;

public class ElectionsService {
    private final StartElectionsDao startElectionsDao;

    public ElectionsService() {
        startElectionsDao = new StartElectionsDaoImpl();
    }

    public String startElections(String requestJsonString) {
        try {
            StartElectionsRequest startRequest = gson.fromJson(requestJsonString, StartElectionsRequest.class);
            startRequest.validate();
            ResultsOfRequests result = startElectionsDao.setElectionsStarted(startRequest.getToken());
            StartElectionsResponse startResponse = new StartElectionsResponse(result);
            return gson.toJson(startResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e);
        }
    }

    public String voteForCandidate(String requestJsonString) {
        try {
            VoteForCandidateRequest voteRequest = gson.fromJson(requestJsonString, VoteForCandidateRequest.class);
            voteRequest.validate();
            VoteForCandidateResponse voteResponse = new VoteForCandidateResponse(startElectionsDao.voteForCandidate(voteRequest.getToken(), voteRequest.getCandidateFullName()));
            return gson.toJson(voteResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e);
        }
    }

    public String chooseMajor(String requestJsonString) {
        try {
            ChooseMajorRequest chooseMajorRequest = gson.fromJson(requestJsonString, ChooseMajorRequest.class);
            chooseMajorRequest.validate();
            ChooseMajorResponse chooseMajorResponse = new ChooseMajorResponse(startElectionsDao.chooseMajor(chooseMajorRequest.getToken()));
            return gson.toJson(chooseMajorResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e);
        }
    }
}

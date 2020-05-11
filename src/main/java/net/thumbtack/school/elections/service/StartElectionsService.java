package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.dao.StartElectionsDao;
import net.thumbtack.school.elections.daoimpl.StartElectionsDaoImpl;
import net.thumbtack.school.elections.dto.request.ChooseMajorDtoRequest;
import net.thumbtack.school.elections.dto.request.StartElectionsDtoRequest;
import net.thumbtack.school.elections.dto.request.VoteForCandidateDtoRequest;
import net.thumbtack.school.elections.dto.response.ChooseMajorDtoResponse;
import net.thumbtack.school.elections.dto.response.StartElectionsDtoResponse;
import net.thumbtack.school.elections.dto.response.VoteForCandidateDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;

import static net.thumbtack.school.elections.server.Server.gson;

public class StartElectionsService {
    private final StartElectionsDao startElectionsDao;

    public StartElectionsService() {
        startElectionsDao = new StartElectionsDaoImpl();
    }

    public String startElections(String requestJsonString) {
        try {
            StartElectionsDtoRequest startRequest = gson.fromJson(requestJsonString, StartElectionsDtoRequest.class);
            startRequest.validate();
            StartElectionsDtoResponse startResponse = new StartElectionsDtoResponse(startElectionsDao.setElectionsStarted(startRequest.getToken()));
            return gson.toJson(startResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String voteForCandidate(String requestJsonString) {
        try {
            VoteForCandidateDtoRequest voteRequest = gson.fromJson(requestJsonString, VoteForCandidateDtoRequest.class);
            voteRequest.validate();
            VoteForCandidateDtoResponse voteResponse = new VoteForCandidateDtoResponse(startElectionsDao.voteForCandidate(voteRequest.getToken(), voteRequest.getCandidateFullName()));
            return gson.toJson(voteResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }

    public String chooseMajor(String requestJsonString) {
        try {
            ChooseMajorDtoRequest chooseMajorRequest = gson.fromJson(requestJsonString, ChooseMajorDtoRequest.class);
            chooseMajorRequest.validate();
            ChooseMajorDtoResponse chooseMajorResponse = new ChooseMajorDtoResponse(startElectionsDao.chooseMajor(chooseMajorRequest.getToken()));
            return gson.toJson(chooseMajorResponse);
        } catch (ElectionsException e) {
            return gson.toJson(e.getErrorCode().getErrorString());
        }
    }
}

package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.ProposalDaoImpl;
import net.thumbtack.school.elections.dto.request.MakeProposalDtoRequest;
import net.thumbtack.school.elections.dto.response.MakeProposalDtoResponse;
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
            MakeProposalDtoResponse makeProposalResponse = new MakeProposalDtoResponse(proposalDao.makeProposal(proposal));
            resultJsonString = new Gson().toJson(makeProposalResponse);
        } catch (ElectionsException e) {
            resultJsonString = new Gson().toJson(e.getErrorCode().getErrorString());
        }
        return resultJsonString;
    }

    public String addRatingForProposal() {

        return resultJsonString;
    }

    public String removeRatingFromProposal(){

        return resultJsonString;
    }

    public String getAllProposals() {

        return resultJsonString;
    }
}

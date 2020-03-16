package net.thumbtack.school.elections.model;

import net.thumbtack.school.elections.dto.request.MakeProposalDtoRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Proposal implements Serializable {

    private static final long serialVersionUID = 5349904395383032185L;

    private String proposalInfo;
    private UUID authorToken;
    private Map<UUID, Integer> rating;

    public Proposal(MakeProposalDtoRequest makeProposalDtoRequest) {
        this.proposalInfo = makeProposalDtoRequest.getProposal();
        this.authorToken = makeProposalDtoRequest.getToken();
        this.rating = new HashMap<>();
        rating.put(this.authorToken, 5);
    }

    public String getProposalInfo() {
        return proposalInfo;
    }

    public UUID getAuthorToken() {
        return authorToken;
    }

    public Map<UUID, Integer> getRating() {
        return rating;
    }
}

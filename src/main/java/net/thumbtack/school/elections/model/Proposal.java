package net.thumbtack.school.elections.model;

import net.thumbtack.school.elections.dto.request.MakeProposalRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Proposal implements Serializable {

    private static final long serialVersionUID = 5349904395383032185L;

    private final UUID defaultAuthorToken = UUID.fromString("f5f313cc-4097-42ad-9d03-dd523389ab69"); //токен всего общества города

    private String proposalInfo;
    private UUID authorToken;
    private Map<UUID, Integer> rating;

    public Proposal(MakeProposalRequest makeProposalDtoRequest) {
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

    public void setDefaultAuthor() {
        this.authorToken = defaultAuthorToken;
    }

    public Map<UUID, Integer> getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Proposal)) {
            return false;
        }
        Proposal proposal = (Proposal) o;
        return Objects.equals(proposalInfo, proposal.proposalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proposalInfo);
    }
}



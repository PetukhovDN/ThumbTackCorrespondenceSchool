package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.Voter;

import java.util.Set;

public class GetAllVotersDtoResponse {
    private Set<Voter> voterList;

    public GetAllVotersDtoResponse(Set<Voter> voterList) {
        this.voterList = voterList;
    }

    public Set<Voter> getVoterList() {
        return voterList;
    }
}

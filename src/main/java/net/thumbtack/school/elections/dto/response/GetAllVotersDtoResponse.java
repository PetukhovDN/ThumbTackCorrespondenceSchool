package net.thumbtack.school.elections.dto.response;

import net.thumbtack.school.elections.model.Voter;

import java.util.List;

public class GetAllVotersDtoResponse {
    private List<Voter> voterList;

    public GetAllVotersDtoResponse(List<Voter> voterList) {
        this.voterList = voterList;
    }

    public List<Voter> getVoterList() {
        return voterList;
    }
}

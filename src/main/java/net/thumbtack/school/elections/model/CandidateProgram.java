package net.thumbtack.school.elections.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CandidateProgram implements Serializable {
    private static final long serialVersionUID = 2160337493925855406L;

    private List<Proposal> proposals;

    public CandidateProgram() {
        this.proposals = new ArrayList<>();
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

}

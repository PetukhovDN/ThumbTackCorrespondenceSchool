package net.thumbtack.school.elections.database;

import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Voter;

import java.io.Serializable;
import java.util.*;

public class Database implements Serializable {

    private static final long serialVersionUID = 577745366661255865L;
    private static Database instance;

    private List<Voter> votersList = new ArrayList<>();
    private Set<UUID> validTokensSet = new HashSet<>();
    private Map<Candidate, Boolean> candidatesList = new HashMap<>();
    private List<Proposal> proposalList = new ArrayList<>();

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public List<Voter> getVotersList() {
        return votersList;
    }

    public Set<UUID> getValidTokensSet() {
        return validTokensSet;
    }


    public Map<Candidate, Boolean> getCandidatesList() { //boolean отвечает за согласие избирателя быть кандидатом в мэры
        return candidatesList;
    }

    public List<Proposal> getProposalList() {
        return proposalList;
    }
}

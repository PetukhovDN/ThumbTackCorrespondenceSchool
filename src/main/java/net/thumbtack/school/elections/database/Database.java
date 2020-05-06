package net.thumbtack.school.elections.database;

import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Voter;

import java.io.Serializable;
import java.util.*;

public class Database implements Serializable {

    private static final long serialVersionUID = 577745366661255865L;
    private static Database instance;
    private final UUID adminToken = UUID.fromString("4d50c72b-8342-4d44-ab09-4026dd0e333d");
    private Map<Voter, UUID> votersMap = new HashMap<>();
    private Map<Candidate, Boolean> candidateMap = new HashMap<>();
    private Set<Proposal> proposalSet = new HashSet<>();
    private Map<Candidate, Integer> candidatesForMajor = new HashMap<>();

    private String electionsStarted = "Выборы еще не начались";

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Map<Voter, UUID> getVotersMap() {
        return votersMap;
    }

    public Map<Candidate, Boolean> getCandidateMap() { //boolean отвечает за согласие избирателя быть кандидатом в мэры
        return candidateMap;
    }

    public Set<Proposal> getProposalSet() {
        return proposalSet;
    }

    public String getElectionsStarted() {
        return electionsStarted;
    }

    public void setElectionsStarted(String electionsStarted) {
        this.electionsStarted = electionsStarted;
    }

    public UUID getAdminToken() {
        return adminToken;
    }

    public Map<Candidate, Integer> getCandidatesForMajor() {
        return candidatesForMajor;
    }

}

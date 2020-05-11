package net.thumbtack.school.elections.database;

import net.thumbtack.school.elections.enums.ElectionsStatus;
import net.thumbtack.school.elections.model.Candidate;
import net.thumbtack.school.elections.model.CandidateProgram;
import net.thumbtack.school.elections.model.Proposal;
import net.thumbtack.school.elections.model.Voter;

import java.io.Serializable;
import java.util.*;

public class Database implements Serializable {

    private static final long serialVersionUID = 577745366661255865L;
    private static Database instance;
    private final UUID adminToken = UUID.fromString("4d50c72b-8342-4d44-ab09-4026dd0e333d");
    private Map<UUID, Voter> votersMap = new HashMap<>();
    private Map<UUID, Candidate> candidateMap = new HashMap<>();
    private Map<String, Proposal> proposalMap = new HashMap<>();
    private Map<String, CandidateProgram> proposalsFromCandidateMap = new HashMap<>();
    private Map<String, Integer> candidatesForMajor = new HashMap<>();
    private Set<UUID> validTokens = new HashSet<>();

    private ElectionsStatus electionsStatus = ElectionsStatus.ELECTIONS_NOT_STARTED;

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Map<UUID, Voter> getVotersMap() {
        return votersMap;
    }

    public Map<UUID, Candidate> getCandidateMap() { //boolean отвечает за согласие избирателя быть кандидатом в мэры
        return candidateMap;
    }

    public Map<String, Proposal> getProposalMap() {
        return proposalMap;
    }

    public Map<String, CandidateProgram> getProposalsFromCandidateMap() {
        return proposalsFromCandidateMap;
    }

    public Map<String, Integer> getCandidatesForMajor() {
        return candidatesForMajor;
    }


    public Set<UUID> getValidTokens() {
        return validTokens;
    }

    public ElectionsStatus getElectionsStatus() {
        return electionsStatus;
    }

    public void setElectionsStatus(ElectionsStatus electionsStatus) {
        this.electionsStatus = electionsStatus;
    }

    public UUID getAdminToken() {
        return adminToken;
    }


}

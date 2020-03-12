package net.thumbtack.school.elections.database;

import net.thumbtack.school.elections.model.Voter;

import java.io.Serializable;
import java.util.*;

public class Database implements Serializable {

    private static final long serialVersionUID = 577745366661255865L;
    private static Database instance;

    private List<Voter> votersList = new ArrayList<>();
    private Set<String> loginVotersSet = new HashSet<>();
    private Set<UUID> validTokensSet = new HashSet<>();
    private Map<String, List<Integer>> proposalsOfAllVoters = new HashMap<>();

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public List<Voter> getVotersList() {
        return votersList;
    }

    public Set<String> getLoginVotersSet() {
        return loginVotersSet;
    }

    public Set<UUID> getValidTokensSet() {
        return validTokensSet;
    }

    public Map<String, List<Integer>> getProposalsOfAllVoters() {
        return proposalsOfAllVoters;
    }
}

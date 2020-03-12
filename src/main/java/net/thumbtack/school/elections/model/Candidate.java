package net.thumbtack.school.elections.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Candidate {
    private String firstName;
    private String lastName;
    private String middleName;
    private String street;
    private int house;
    private int flat;
    private String login;
    private String password;
    private UUID token;

    private List<String> proposalList;

    public Candidate(Voter voter) {
        this.firstName = voter.getFirstName();
        this.lastName = voter.getLastName();
        this.middleName = voter.getMiddleName();
        this.street = voter.getStreet();
        this.house = voter.getHouse();
        this.flat = voter.getFlat();
        this.login = voter.getLogin();
        this.password = voter.getPassword();
        this.token = voter.getToken();
        this.proposalList = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getFlat() {
        return flat;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UUID getToken() {
        return token;
    }

    public List<String> getProposalList() {
        return proposalList;
    }
}


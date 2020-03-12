package net.thumbtack.school.elections.model;

import java.io.Serializable;
import java.util.UUID;

public class Candidate implements Serializable {

    private static final long serialVersionUID = 7436256402214609702L;

    private String firstName;
    private String lastName;
    private String middleName;
    private String street;
    private int house;
    private int flat;
    private String login;
    private String password;
    private UUID token;

    private CandidateProgram candidateProgram;

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
        this.candidateProgram = new CandidateProgram();
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

    public CandidateProgram getCandidateProgram() {
        return candidateProgram;
    }
}


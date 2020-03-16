package net.thumbtack.school.elections.model;

import net.thumbtack.school.elections.dto.request.AddCandidateDtoRequest;

import java.io.Serializable;

public class Candidate implements Serializable {

    private static final long serialVersionUID = -3872129141080909473L;

    private String firstName;
    private String lastName;

    public Candidate(AddCandidateDtoRequest candidateDtoRequest) {
        this.firstName = candidateDtoRequest.getFirstName();
        this.lastName = candidateDtoRequest.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}


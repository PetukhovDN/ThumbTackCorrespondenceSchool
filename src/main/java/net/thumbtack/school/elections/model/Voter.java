package net.thumbtack.school.elections.model;

import net.thumbtack.school.elections.dto.request.RegisterVoterRequest;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Voter implements Serializable {

    private static final long serialVersionUID = 578230562316899139L;

    private String firstName;
    private String lastName;
    private String fullName;
    private String middleName;
    private String street;
    private int house;
    private int flat;
    private String login;
    private String password;
    private UUID token;

    public Voter(RegisterVoterRequest registerVoterDtoRequest) {
        this.firstName = registerVoterDtoRequest.getFirstName();
        this.lastName = registerVoterDtoRequest.getLastName();
        this.fullName = this.firstName + " " + this.lastName;
        this.middleName = registerVoterDtoRequest.getMiddleName();
        this.street = registerVoterDtoRequest.getStreet();
        this.house = registerVoterDtoRequest.getHouse();
        this.flat = registerVoterDtoRequest.getFlat();
        this.login = registerVoterDtoRequest.getLogin();
        this.password = registerVoterDtoRequest.getPassword();
        setToken(UUID.randomUUID());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
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

    public void setToken(UUID token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Voter)) {
            return false;
        }
        Voter voter = (Voter) o;
        return firstName.equals(voter.firstName) &&
                lastName.equals(voter.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}



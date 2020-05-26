package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorInfo;

import java.util.UUID;

public class AddCandidateRequest {
    private String firstName;
    private String lastName;
    private UUID token;

    public AddCandidateRequest(String firstName, String lastName, UUID token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UUID getToken() {
        return token;
    }

    public void validate() throws ElectionsException {
        if (this.firstName == null || this.firstName.isEmpty()) {
            throw new ElectionsException(ExceptionErrorInfo.EMPTY_VOTER_FIRSTNAME);
        }
        if (this.lastName == null || this.lastName.isEmpty()) {
            throw new ElectionsException(ExceptionErrorInfo.EMPTY_VOTER_LASTNAME);
        }
        if (this.token == null || this.token.toString().isEmpty()) {
            throw new ElectionsException(ExceptionErrorInfo.WRONG_VOTER_TOKEN);
        }
    }
}

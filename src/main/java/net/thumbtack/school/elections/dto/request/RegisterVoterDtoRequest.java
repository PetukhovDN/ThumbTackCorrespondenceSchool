package net.thumbtack.school.elections.dto.request;

import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.exceptions.ElectionsException;

public class RegisterVoterDtoRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String street;
    private int house;
    private int flat;
    private String login;
    private String password;

    public RegisterVoterDtoRequest(String firstName, String lastName, String middleName, String street, int house, int flat, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.login = login;
        this.password = password;
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

    public void validate() throws ElectionsException {
        if (this.firstName == null || this.firstName.isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_FIRSTNAME);
        }
        if (this.lastName == null || this.lastName.isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_LASTNAME);
        }
        if (this.street == null || this.street.isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_STREET);
        }
        if (this.house == 0) {
            throw new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_HOUSE);
        }
        if (this.login == null || this.login.isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_LOGIN);
        }
        if (this.login.length() < 3) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_LOGIN_LENGTH);
        }
        if (this.password == null || this.password.isEmpty()) {
            throw new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_PASSWORD);
        }
        if (this.password.length() < 8) {
            throw new ElectionsException(ExceptionErrorCode.WRONG_VOTER_PASSWORD_LENGTH);
        }
    }
}

package net.thumbtack.school.elections.exceptions;

public class VoterException extends Throwable {
    ExceptionErrorCode exceptionErrorCode;

    public VoterException(ExceptionErrorCode exceptionErrorCode) {
        this.exceptionErrorCode = exceptionErrorCode;
    }

    public ExceptionErrorCode getErrorCode() {
        return exceptionErrorCode;
    }
}

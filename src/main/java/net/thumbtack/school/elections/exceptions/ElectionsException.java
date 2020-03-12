package net.thumbtack.school.elections.exceptions;

public class ElectionsException extends Throwable {
    private ExceptionErrorCode exceptionErrorCode;

    public ElectionsException(ExceptionErrorCode exceptionErrorCode) {
        this.exceptionErrorCode = exceptionErrorCode;
    }

    public ExceptionErrorCode getErrorCode() {
        return exceptionErrorCode;
    }
}

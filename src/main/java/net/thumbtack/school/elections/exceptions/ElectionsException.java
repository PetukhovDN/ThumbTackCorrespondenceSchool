package net.thumbtack.school.elections.exceptions;

public class ElectionsException extends Throwable {
    private ExceptionErrorInfo exceptionErrorInfo;

    public ElectionsException(ExceptionErrorInfo exceptionErrorInfo) {
        this.exceptionErrorInfo = exceptionErrorInfo;
    }

    public ExceptionErrorInfo getExceptionErrorInfo() {
        return exceptionErrorInfo;
    }
}

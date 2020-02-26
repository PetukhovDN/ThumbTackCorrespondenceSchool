package net.thumbtack.school.ttschool;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("Неверное имя"),
    TRAINEE_WRONG_LASTNAME("Неверная фамилия"),
    TRAINEE_WRONG_RATING("Неверная оценка");

    // REVU Члены классов должны быть по возможности приватными
    String errorString;

    TrainingErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}

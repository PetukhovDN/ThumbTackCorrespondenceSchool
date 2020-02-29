package net.thumbtack.school.ttschool;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("Неверное имя"),
    TRAINEE_WRONG_LASTNAME("Неверная фамилия"),
    TRAINEE_WRONG_RATING("Неверная оценка"),
    GROUP_WRONG_NAME("Неверное название группы"),
    GROUP_WRONG_ROOM("Неверное название аудитории"),
    TRAINEE_NOT_FOUND("Студент не найден"),
    SCHOOL_WRONG_NAME("Неверное название школы"),
    DUPLICATE_GROUP_NAME("Такое название группы уже есть"),
    GROUP_NOT_FOUND("Такой группы нет"),
    DUPLICATE_TRAINEE("Такой студент уже есть"),
    EMPTY_TRAINEE_QUEUE("Очередь студентов пуста");

    private String errorString;

    TrainingErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}

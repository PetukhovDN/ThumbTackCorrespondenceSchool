package net.thumbtack.school.ttschool;

public class TrainingException extends Exception {
    // REVU Поля классов должны быть private
    TrainingErrorCode trainingErrorCode;

    public TrainingException(TrainingErrorCode trainingErrorCode) {
        this.trainingErrorCode = trainingErrorCode;
    }

    public TrainingErrorCode getErrorCode() {
        return trainingErrorCode;
    }
}

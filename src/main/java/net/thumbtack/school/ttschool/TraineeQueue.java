package net.thumbtack.school.ttschool;

import java.util.ArrayDeque;
import java.util.Queue;

public class TraineeQueue {
    Queue<Trainee> queue;

    public TraineeQueue() {
        queue = new ArrayDeque<>();
    }

    public void addTrainee(Trainee trainee) {
        queue.add(trainee);
    }

    public Trainee removeTrainee() throws TrainingException {
        Trainee trainee = queue.poll();
        if (trainee == null) {
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        }
        return trainee;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

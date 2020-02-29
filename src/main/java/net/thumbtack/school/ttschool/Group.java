package net.thumbtack.school.ttschool;

import java.util.*;
import java.util.stream.Collectors;

public class Group {
    private String name;
    private String room;
    private List<Trainee> list = new ArrayList<>();

    public Group(String name, String room) throws TrainingException {
        setName(name);
        setRoom(room);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return list;
    }

    public void addTrainee(Trainee trainee) {
        list.add(trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        if (!list.remove(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public void removeTrainee(int index) throws TrainingException {
        if (index < 0 || index >= list.size()) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        list.remove(index);
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        for (Trainee trainee : list) {
            if (trainee.getFirstName().equals(firstName)) {
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
//        Trainee trainee = list.stream().filter(t -> t.getFirstName().equals(firstName)).findFirst().orElse(null);
//        if (trainee == null) {
//            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
//        }
//        return trainee;
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        for (Trainee trainee : list) {
            if (trainee.getFullName().equals(fullName)) {
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void sortTraineeListByFirstNameAscendant() {
        list.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public void sortTraineeListByRatingDescendant() {
        list.sort((p1, p2) -> p2.getRating() - p1.getRating());
    }

    public void reverseTraineeList() {
        Collections.reverse(list);
    }

    public void rotateTraineeList(int positions) {
        Collections.rotate(list, positions);
    }

    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {
        int bestRating = 0;
        for (Trainee trainee : list) {
            if (trainee.getRating() > bestRating) {
                bestRating = trainee.getRating();
            }
        }
        int a = bestRating;
        List<Trainee> bestTrainees = list.stream().filter(tr -> tr.getRating() == a).collect(Collectors.toList());
        if (bestTrainees.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return bestTrainees;
    }

    public boolean hasDuplicates() {
        Set<Trainee> set = new HashSet<>(list);
        return set.size() < list.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Group)) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(list, group.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, list);
    }
}

package net.thumbtack.school.ttschool;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestTraineeMap {

    @Test
    public void testAddTraineeInfo() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        TraineeMap traineeMap = new TraineeMap();
        traineeMap.addTraineeInfo(trainee1, "ОмГУ");
        traineeMap.addTraineeInfo(trainee2, "ОмГУ");
        assertEquals(2, traineeMap.getTraineesCount());
    }

    @Test
    public void testAddDuplicateTraineeInfo() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Иван", "Иванов", 1);
        TraineeMap traineeMap = new TraineeMap();
        traineeMap.addTraineeInfo(trainee1, "ОмГУ");
        try {
            traineeMap.addTraineeInfo(trainee2, "ОмГУ");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.DUPLICATE_TRAINEE, ex.getErrorCode());
        }
    }

    @Test
    public void testReplaceTraineeInfo() throws TrainingException {
        Trainee trainee = new Trainee("Иван", "Иванов", 1);
        TraineeMap traineeMap = new TraineeMap();
        traineeMap.addTraineeInfo(trainee, "ОмГУ");
        assertEquals(1, traineeMap.getTraineesCount());
        assertEquals("ОмГУ", traineeMap.getInstituteByTrainee(trainee));
        traineeMap.replaceTraineeInfo(trainee, "ОмГТУ");
        assertEquals(1, traineeMap.getTraineesCount());
        assertEquals("ОмГТУ", traineeMap.getInstituteByTrainee(trainee));
    }

    @Test
    public void testReplaceNonExistentTraineeInfo() throws TrainingException {
        Trainee trainee = new Trainee("Иван", "Иванов", 1);
        TraineeMap traineeMap = new TraineeMap();
        try {
            traineeMap.replaceTraineeInfo(trainee, "ОмГТУ");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());
        }
    }

    @Test
    public void testRemoveTraineeInfo() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        TraineeMap traineeMap = new TraineeMap();
        traineeMap.addTraineeInfo(trainee1, "ОмГУ");
        traineeMap.addTraineeInfo(trainee2, "ОмГУ");
        assertEquals(2, traineeMap.getTraineesCount());
        traineeMap.removeTraineeInfo(trainee2);
        assertEquals(1, traineeMap.getTraineesCount());
    }

    @Test
    public void testRemoveNonExistentTraineeInfo() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        TraineeMap traineeMap = new TraineeMap();
        traineeMap.addTraineeInfo(trainee1, "ОмГУ");
        try {
            traineeMap.removeTraineeInfo(trainee2);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());
        }
    }

    @Test
    public void testGetInstituteByTrainee() throws TrainingException {
        Trainee trainee = new Trainee("Иван", "Иванов", 1);
        TraineeMap traineesMap = new TraineeMap();
        traineesMap.addTraineeInfo(trainee, "ОмГУ");
        String institute = traineesMap.getInstituteByTrainee(trainee);
        assertEquals("ОмГУ", institute);
    }

    @SuppressWarnings("unused")
    @Test
    public void testGetInstituteByNonExistentTrainee() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        TraineeMap traineeMap = new TraineeMap();
        traineeMap.addTraineeInfo(trainee1, "ОмГУ");
        try {
            String institute = traineeMap.getInstituteByTrainee(trainee2);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());

        }
    }

    @Test
    public void testGetAllTrainees() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        TraineeMap traineeMap = new TraineeMap();
        traineeMap.addTraineeInfo(trainee1, "ОмГУ");
        traineeMap.addTraineeInfo(trainee2, "ОмГУ");
        Set<Trainee> set = traineeMap.getAllTrainees();
        assertEquals(2, set.size());
        assertTrue(set.contains(trainee1));
        assertTrue(set.contains(trainee2));
    }

    @Test
    public void testGetAllInstitutes() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        Trainee trainee3 = new Trainee("Николай", "Николаев", 1);
        TraineeMap traineeMap = new TraineeMap();
        traineeMap.addTraineeInfo(trainee1, "ОмГУ");
        traineeMap.addTraineeInfo(trainee2, "ОмГУ");
        traineeMap.addTraineeInfo(trainee3, "ОмГТУ");
        Set<String> institutes = traineeMap.getAllInstitutes();
        assertEquals(2, institutes.size());
    }

    @Test
    public void testAnyFromInstitute() throws TrainingException {
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 1);
        Trainee trainee3 = new Trainee("Николай", "Николаев", 1);
        TraineeMap traineeMap = new TraineeMap();
        traineeMap.addTraineeInfo(trainee1, "ОмГУ");
        traineeMap.addTraineeInfo(trainee2, "ОмГУ");
        traineeMap.addTraineeInfo(trainee3, "ОмГТУ");
        assertTrue(traineeMap.isAnyFromInstitute("ОмГУ"));
        assertTrue(traineeMap.isAnyFromInstitute("ОмГТУ"));
        assertFalse(traineeMap.isAnyFromInstitute("ОмГПУ"));
    }
}

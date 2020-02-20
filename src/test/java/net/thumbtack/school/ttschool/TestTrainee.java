package net.thumbtack.school.ttschool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestTrainee {

    @Test
    public void testTrainee() throws TrainingException {
        Trainee trainee = new Trainee("Иван", "Иванов", 1);
        assertEquals("Иван", trainee.getFirstName());
        assertEquals("Иванов", trainee.getLastName());
        assertEquals("Иван Иванов", trainee.getFullName());
        assertEquals(1, trainee.getRating());
        trainee.setFirstName("Петр");
        assertEquals("Петр", trainee.getFirstName());
        trainee.setLastName("Петров");
        assertEquals("Петров", trainee.getLastName());
        trainee.setRating(2);
        assertEquals(2, trainee.getRating());

    }

    @SuppressWarnings("unused")
    @Test
    public void testWrongFirstName() {

        try {
            Trainee trainee = new Trainee(null, "Иванов", 1);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME, ex.getErrorCode());
        }

        try {
            Trainee trainee = new Trainee("", "Иванов", 1);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME, ex.getErrorCode());
        }

        try {
            Trainee trainee = new Trainee("Иван", "Иванов", 1);
            trainee.setFirstName(null);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME, ex.getErrorCode());
        }

        try {
            Trainee trainee = new Trainee("Иван", "Иванов", 1);
            trainee.setFirstName("");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME, ex.getErrorCode());
        }
    }

    @SuppressWarnings("unused")
    @Test
    public void testWrongLastName() {

        try {
            Trainee trainee = new Trainee("Иван", null, 1);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_LASTNAME, ex.getErrorCode());
        }

        try {
            Trainee trainee = new Trainee("Иван", null, 1);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_LASTNAME, ex.getErrorCode());
        }

        try {
            Trainee trainee = new Trainee("Иван", "Иванов", 1);
            trainee.setLastName(null);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_LASTNAME, ex.getErrorCode());
        }

        try {
            Trainee trainee = new Trainee("Иван", "Иванов", 1);
            trainee.setLastName("");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_LASTNAME, ex.getErrorCode());
        }
    }

    @SuppressWarnings("unused")
    @Test
    public void testWrongRating() {

        try {
            Trainee trainee = new Trainee("Иван", "Иванов", 0);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_RATING, ex.getErrorCode());
        }

        try {
            Trainee trainee = new Trainee("Иван", "Иванов", 6);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_RATING, ex.getErrorCode());
        }

        try {
            Trainee trainee = new Trainee("Иван", "Иванов", 1);
            trainee.setRating(0);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_RATING, ex.getErrorCode());
        }
        try {
            Trainee trainee = new Trainee("Иван", "Иванов", 1);
            trainee.setRating(6);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_WRONG_RATING, ex.getErrorCode());
        }

    }
}
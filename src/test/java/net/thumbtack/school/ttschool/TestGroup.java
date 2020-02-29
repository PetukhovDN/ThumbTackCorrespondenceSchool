package net.thumbtack.school.ttschool;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGroup {

    @Test
    public void testEmptyGroup() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        assertEquals("Frontend", group.getName());
        assertEquals("7-1", group.getRoom());
        assertEquals(0, group.getTrainees().size());
        group.setName("Backend");
        group.setRoom("6");
        assertEquals("Backend", group.getName());
        assertEquals("6", group.getRoom());
    }

    @SuppressWarnings("unused")
    @Test
    public void testWrongName() {

        try {
            Group group = new Group("", "7-1");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.GROUP_WRONG_NAME, ex.getErrorCode());
        }

        try {
            Group group = new Group(null, "7-1");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.GROUP_WRONG_NAME, ex.getErrorCode());
        }

        try {
            Group group = new Group("Frontend", "7-1");
            group.setName(null);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.GROUP_WRONG_NAME, ex.getErrorCode());
        }

        try {
            Group group = new Group("Frontend", "7-1");
            group.setName("");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.GROUP_WRONG_NAME, ex.getErrorCode());
        }
    }

    @SuppressWarnings("unused")
    @Test
    public void testWrongRoom() {

        try {
            Group group = new Group("Backend", null);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.GROUP_WRONG_ROOM, ex.getErrorCode());
        }

        try {
            Group group = new Group("Backend", "");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.GROUP_WRONG_ROOM, ex.getErrorCode());
        }

        try {
            Group group = new Group("Frontend", "7-1");
            group.setRoom(null);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.GROUP_WRONG_ROOM, ex.getErrorCode());
        }

        try {
            Group group = new Group("Frontend", "7-1");
            group.setRoom("");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.GROUP_WRONG_ROOM, ex.getErrorCode());
        }
    }

    @Test
    public void testAddTraineeToGroup() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        assertEquals(2, group.getTrainees().size());
        assertEquals(trainee1, group.getTrainees().get(0));
        assertEquals(trainee2, group.getTrainees().get(1));
    }

    @Test
    public void testRemoveTraineeFromGroup() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        assertEquals(2, group.getTrainees().size());
        group.removeTrainee(trainee1);
        assertEquals(trainee2, group.getTrainees().get(0));
    }

    @Test
    public void testRemoveNonexistentTraineeFromGroup() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        assertEquals(2, group.getTrainees().size());
        Trainee trainee3 = new Trainee("Сидор", "Сидоров", 3);
        try {
            group.removeTrainee(trainee3);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());
            assertEquals(2, group.getTrainees().size());
        }
    }

    @Test
    public void testRemoveTraineeFromGroupByIndex() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        assertEquals(2, group.getTrainees().size());
        group.removeTrainee(0);
        assertEquals(trainee2, group.getTrainees().get(0));
    }

    @Test
    public void testRemoveTraineeFromGroupByWrongIndex() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        assertEquals(2, group.getTrainees().size());
        try {
            group.removeTrainee(2);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());
            assertEquals(2, group.getTrainees().size());
        }
    }

    @Test
    public void testFindTraineeByFirstName() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        assertEquals(2, group.getTrainees().size());
        Trainee found = group.getTraineeByFirstName("Иван");
        assertEquals(trainee1, found);
        try {
            group.getTraineeByFirstName("Василий");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());
        }
    }

    @Test
    public void testFindTraineeByFullName() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Иван", "Петров", 2);
        Trainee trainee3 = new Trainee("Петр", "Иванов", 2);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        group.addTrainee(trainee3);
        assertEquals(3, group.getTrainees().size());
        Trainee found = group.getTraineeByFullName("Иван Иванов");
        assertEquals(trainee1, found);
        try {
            group.getTraineeByFullName("Петр Петров");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());
        }
    }

    @Test
    public void testSortTraineeListByFirstName() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Федор", "Петров", 2);
        Trainee trainee3 = new Trainee("Петр", "Иванов", 2);
        Trainee trainee4 = new Trainee("Александр", "Николаев", 2);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        group.addTrainee(trainee3);
        group.addTrainee(trainee4);
        assertEquals(4, group.getTrainees().size());
        group.sortTraineeListByFirstNameAscendant();
        assertEquals(trainee4, group.getTrainees().get(0));
        assertEquals(trainee1, group.getTrainees().get(1));
        assertEquals(trainee3, group.getTrainees().get(2));
        assertEquals(trainee2, group.getTrainees().get(3));
    }

    @Test
    public void testSortTraineeListByRating() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 5);
        Trainee trainee2 = new Trainee("Федор", "Петров", 2);
        Trainee trainee3 = new Trainee("Петр", "Иванов", 3);
        Trainee trainee4 = new Trainee("Александр", "Николаев", 1);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        group.addTrainee(trainee3);
        group.addTrainee(trainee4);
        assertEquals(4, group.getTrainees().size());
        group.sortTraineeListByRatingDescendant();
        assertEquals(trainee1, group.getTrainees().get(0));
        assertEquals(trainee3, group.getTrainees().get(1));
        assertEquals(trainee2, group.getTrainees().get(2));
        assertEquals(trainee4, group.getTrainees().get(3));
    }

    @Test
    public void testReverseTraineeList() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 5);
        Trainee trainee2 = new Trainee("Федор", "Петров", 2);
        Trainee trainee3 = new Trainee("Петр", "Иванов", 3);
        Trainee trainee4 = new Trainee("Александр", "Николаев", 1);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        group.addTrainee(trainee3);
        group.addTrainee(trainee4);
        assertEquals(4, group.getTrainees().size());
        group.reverseTraineeList();
        assertEquals(trainee4, group.getTrainees().get(0));
        assertEquals(trainee3, group.getTrainees().get(1));
        assertEquals(trainee2, group.getTrainees().get(2));
        assertEquals(trainee1, group.getTrainees().get(3));
    }

    @Test
    public void testRotateTraineeList() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 5);
        Trainee trainee2 = new Trainee("Федор", "Петров", 2);
        Trainee trainee3 = new Trainee("Петр", "Иванов", 3);
        Trainee trainee4 = new Trainee("Александр", "Николаев", 1);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        group.addTrainee(trainee3);
        group.addTrainee(trainee4);
        assertEquals(4, group.getTrainees().size());
        group.rotateTraineeList(2);
        assertEquals(trainee1, group.getTrainees().get(2));
        assertEquals(trainee2, group.getTrainees().get(3));
        assertEquals(trainee3, group.getTrainees().get(0));
        assertEquals(trainee4, group.getTrainees().get(1));
    }

    @Test
    public void testGetTraineesWithMaxRating() throws TrainingException {
        Group group1 = new Group("Frontend", "7-1");
        Trainee trainee11 = new Trainee("Федор", "Петров", 2);
        Trainee trainee12 = new Trainee("Иван", "Иванов", 5);
        Trainee trainee13 = new Trainee("Петр", "Иванов", 5);
        Trainee trainee14 = new Trainee("Александр", "Николаев", 1);
        group1.addTrainee(trainee11);
        group1.addTrainee(trainee12);
        group1.addTrainee(trainee13);
        group1.addTrainee(trainee14);
        assertEquals(4, group1.getTrainees().size());
        List<Trainee> maxList1 = group1.getTraineesWithMaxRating();
        assertEquals(2, maxList1.size());
        assertEquals(5, maxList1.get(0).getRating());
        assertEquals(5, maxList1.get(1).getRating());
        Group group2 = new Group("Backend", "5");
        Trainee trainee21 = new Trainee("Иван", "Иванов", 4);
        Trainee trainee22 = new Trainee("Федор", "Петров", 3);
        Trainee trainee23 = new Trainee("Петр", "Иванов", 2);
        Trainee trainee24 = new Trainee("Александр", "Николаев", 4);
        group2.addTrainee(trainee21);
        group2.addTrainee(trainee22);
        group2.addTrainee(trainee23);
        group2.addTrainee(trainee24);
        assertEquals(4, group2.getTrainees().size());
        List<Trainee> maxList2 = group2.getTraineesWithMaxRating();
        assertEquals(2, maxList2.size());
        assertEquals(4, maxList2.get(0).getRating());
        assertEquals(4, maxList2.get(1).getRating());
    }

    @Test
    public void testGetTraineesWithMaxRatingForEmptyGroup() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        try {
            @SuppressWarnings("unused")
            List<Trainee> maxList = group.getTraineesWithMaxRating();
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.TRAINEE_NOT_FOUND, ex.getErrorCode());
        }

    }

    @Test
    public void testHasDuplicates() throws TrainingException {
        Group group = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Петр", "Петров", 2);
        group.addTrainee(trainee1);
        group.addTrainee(trainee2);
        assertFalse(group.hasDuplicates());
        Trainee trainee3 = new Trainee("Иван", "Иванов", 1);
        group.addTrainee(trainee3);
        assertTrue(group.hasDuplicates());
    }

    @Test
    public void testEqualGroups() throws TrainingException {
        Group group1 = new Group("Frontend", "7-1");
        Trainee trainee1 = new Trainee("Иван", "Иванов", 1);
        Trainee trainee2 = new Trainee("Федор", "Петров", 2);
        Trainee trainee3 = new Trainee("Петр", "Иванов", 2);
        group1.addTrainee(trainee1);
        group1.addTrainee(trainee2);
        group1.addTrainee(trainee3);
        Group group2 = new Group("Frontend", "7-1");
        group2.addTrainee(trainee1);
        group2.addTrainee(trainee2);
        group2.addTrainee(trainee3);
        assertEquals(group1, group2);
        Trainee trainee4 = new Trainee("Александр", "Николаев", 2);
        group2.addTrainee(trainee4);
        assertNotEquals(group1, group2);
    }
}

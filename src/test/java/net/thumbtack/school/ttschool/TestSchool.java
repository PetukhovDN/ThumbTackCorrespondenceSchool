package net.thumbtack.school.ttschool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSchool {

    @Test
    public void testEmptySchool() throws TrainingException {
        School school = new School("TT Distance", 2018);
        assertEquals("TT Distance", school.getName());
        assertEquals(2018, school.getYear());
        assertEquals(0, school.getGroups().size());
        school.setName("Дистанционная Школа ТТ");
        school.setYear(2019);
        assertEquals("Дистанционная Школа ТТ", school.getName());
        assertEquals(2019, school.getYear());
    }

    @SuppressWarnings("unused")
    @Test
    public void testWrongName() {

        try {
            School School = new School("", 2018);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.SCHOOL_WRONG_NAME, ex.getErrorCode());
        }

        try {
            School School = new School(null, 2018);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.SCHOOL_WRONG_NAME, ex.getErrorCode());
        }

        try {
            School School = new School("TT Distance", 2018);
            School.setName(null);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.SCHOOL_WRONG_NAME, ex.getErrorCode());
        }

        try {
            School School = new School("TT Distance", 2018);
            School.setName("");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.SCHOOL_WRONG_NAME, ex.getErrorCode());
        }
    }

    @Test
    public void testAddGroupToSchool() throws TrainingException {
        Group group1 = new Group("Web Full Stack", "7-1");
        Group group2 = new Group("Backend", "7-2");
        School school = new School("TT Distance", 2018);
        school.addGroup(group1);
        school.addGroup(group2);
        assertEquals(2, school.getGroups().size());
    }

    @Test
    public void testAddGroupToSchoolWithExistentName() throws TrainingException {
        Group group1 = new Group("Web Full Stack", "7-1");
        Group group2 = new Group("Web Full Stack", "7-2");
        School school = new School("TT Distance", 2018);
        school.addGroup(group1);
        try {
            school.addGroup(group2);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.DUPLICATE_GROUP_NAME, ex.getErrorCode());
        }
    }

    @Test
    public void testRemoveGroupFromSchool() throws TrainingException {
        Group group1 = new Group("Web Full Stack", "7-1");
        Group group2 = new Group("Backend", "7-2");
        School school = new School("TT Distance", 2018);
        school.addGroup(group1);
        school.addGroup(group2);
        assertEquals(2, school.getGroups().size());
        school.removeGroup(group2);
        assertEquals(1, school.getGroups().size());
        assertTrue(school.getGroups().contains(group1));
    }

    @Test
    public void testRemoveGroupByNameFromSchool() throws TrainingException {
        Group group1 = new Group("Web Full Stack", "7-1");
        Group group2 = new Group("Backend", "7-2");
        School school = new School("TT Distance", 2018);
        school.addGroup(group1);
        school.addGroup(group2);
        assertEquals(2, school.getGroups().size());
        school.removeGroup(group2.getName());
        assertEquals(1, school.getGroups().size());
        assertTrue(school.getGroups().contains(group1));
    }

    @Test
    public void testRemoveNonExistentGroupFromSchool() throws TrainingException {
        Group group1 = new Group("Web Full Stack", "7-1");
        Group group2 = new Group("Backend", "7-2");
        School school = new School("TT Distance", 2018);
        school.addGroup(group1);
        school.addGroup(group2);
        assertEquals(2, school.getGroups().size());
        Group group3 = new Group("Mobile", "7-3");
        try {
            school.removeGroup(group3);
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.GROUP_NOT_FOUND, ex.getErrorCode());
        }
    }

    @Test
    public void testRemoveGroupWithNonExistentNameFromSchool() throws TrainingException {
        Group group1 = new Group("Web Full Stack", "7-1");
        Group group2 = new Group("Backend", "7-2");
        School school = new School("TT Distance", 2018);
        school.addGroup(group1);
        school.addGroup(group2);
        assertEquals(2, school.getGroups().size());
        try {
            school.removeGroup("Mobile");
            fail();
        } catch (TrainingException ex) {
            assertEquals(TrainingErrorCode.GROUP_NOT_FOUND, ex.getErrorCode());
        }
    }

    @Test
    public void testSchoolContainsGroup() throws TrainingException {
        Group group1 = new Group("Web Full Stack", "7-1");
        Group group2 = new Group("Backend", "7-2");
        School school = new School("TT Distance", 2018);
        school.addGroup(group1);
        school.addGroup(group2);
        assertTrue(school.containsGroup(group1));
    }

    @Test
    public void testEqualsSchool() throws TrainingException {
        Group group11 = new Group("Web Full Stack", "7-1");
        Group group12 = new Group("Backend", "7-2");
        School school1 = new School("TT Distance", 2018);
        school1.addGroup(group11);
        school1.addGroup(group12);
        Group group21 = new Group("Web Full Stack", "7-1");
        Group group22 = new Group("Backend", "7-2");
        School school2 = new School("TT Distance", 2018);
        school2.addGroup(group21);
        school2.addGroup(group22);
        assertEquals(school1, school2);
    }
}

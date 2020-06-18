package net.thumbtack.school.database.mybatis;


import java.util.*;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestFullSchoolOperations extends TestBase {


    @Test
    public void testInsertSchoolWithGroupsAndTrainees() {
        School school2018 = insertTTSchool("TTSchool", 2018);
        Group groupFrontEnd = insertGroup(school2018, "BackEnd", 2018);
        Group groupBackEnd = insertGroup(school2018, "FrontEnd", 2018);
        insertFrontEndTrainees(groupFrontEnd);
        insertBackendTrainees(groupBackEnd);
        List<Group> groups = new ArrayList<>();
        groups.add(groupFrontEnd);
        groups.add(groupBackEnd);
        school2018.setGroups(groups);
        School schoolFromDB = schoolDao.getById(school2018.getId());
        for (Group group : schoolFromDB.getGroups()) {
            group.getTrainees().sort(Comparator.comparingInt(Trainee::getId));
        }
        assertEquals(school2018, schoolFromDB);
    }


    @Test
    public void testInsertSchoolWithGroupsAndSubjectsAndTrainees() {
        School school2018 = insertTTSchool("TTSchool", 2018);
        Map<String, Subject> subjects = insertSubjects("Linux", "MySQL", "NodeJS");
        Group groupFrontEnd = insertFrontEndGroupWithSubjects(school2018, 2018, subjects);
        Group groupBackEnd = insertBackEndGroupWithSubjects(school2018, 2018, subjects);
        insertFrontEndTrainees(groupFrontEnd);
        insertBackendTrainees(groupBackEnd);
        List<Group> groups = new ArrayList<>();
        groups.add(groupFrontEnd);
        groups.add(groupBackEnd);
        school2018.setGroups(groups);
        School schoolFromDB = schoolDao.getById(school2018.getId());
        for (Group group : schoolFromDB.getGroups()) {
            group.getTrainees().sort(Comparator.comparingInt(Trainee::getId));
        }

        assertEquals(school2018, schoolFromDB);
    }

    @Test
    public void testInsertSchoolWithGroupsAndTraineesTransactional() {
        Map<String, Subject> subjects = insertSubjects("Linux", "MySQL", "NodeJS");
        School school2018 = new School("TTSchool", 2018);
        Group groupFrontEnd = new Group("Frontend 2018", "11");
        groupFrontEnd.addSubject(subjects.get("Linux"));
        groupFrontEnd.addSubject(subjects.get("NodeJS"));
        Group groupBackEnd = new Group("Backend 2018", "12");
        groupBackEnd.addSubject(subjects.get("Linux"));
        groupBackEnd.addSubject(subjects.get("MySQL"));
        Trainee traineeIvanov = new Trainee("Иван", "Иванов", 5);
        Trainee traineePetrov = new Trainee("Петр", "Петров", 4);
        groupFrontEnd.addTrainee(traineeIvanov);
        groupFrontEnd.addTrainee(traineePetrov);
        Trainee traineeSidorov = new Trainee("Сидор", "Сидоров", 2);
        Trainee traineeSmirnov = new Trainee("Николай", "Смирнов", 3);
        groupBackEnd.addTrainee(traineeSidorov);
        groupBackEnd.addTrainee(traineeSmirnov);
        List<Group> groups = new ArrayList<>();
        groups.add(groupFrontEnd);
        groups.add(groupBackEnd);
        school2018.setGroups(groups);
        schoolDao.insertSchoolTransactional(school2018);
        School schoolFromDB = schoolDao.getById(school2018.getId());
        assertEquals(school2018, schoolFromDB);
    }
}

package net.thumbtack.school.database.mybatis;

import net.thumbtack.school.database.mybatis.dao.*;
import net.thumbtack.school.database.mybatis.daoimpl.*;
import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import net.thumbtack.school.database.mybatis.utils.MyBatisUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestBase {

    protected SchoolDao schoolDao = new SchoolDaoImpl();
    protected GroupDao groupDao = new GroupDaoImpl();
    protected TraineeDao traineeDao = new TraineeDaoImpl();
    protected SubjectDao subjectDao = new SubjectDaoImpl();

    private static boolean setUpIsDone = false;

    @BeforeAll()
    public static void setUp() {
        if (!setUpIsDone) {
            boolean initSqlSessionFactory = MyBatisUtils.initSqlSessionFactory();
            if (!initSqlSessionFactory) {
                throw new RuntimeException("Can't create connection, stop");
            }
            setUpIsDone = true;
        }
    }

    @BeforeEach()
    public void clearDatabase() {
        traineeDao.deleteAll();
        schoolDao.deleteAll();
        subjectDao.deleteAll();
    }

    protected Trainee insertTrainee(String firstName, String lastName, int rating) {
        Trainee trainee = new Trainee(firstName, lastName, rating);
        traineeDao.insert(null, trainee);
        assertNotEquals(0, trainee.getId());
        return trainee;
    }

    protected Subject insertSubject(String name) {
        Subject subject = new Subject(name);
        subjectDao.insert(subject);
        assertNotEquals(0, subject.getId());
        return subject;
    }

    protected School insertTTSchool(String name, int year) {
        School school = new School(name, year);
        schoolDao.insert(school);
        assertNotEquals(0, school.getId());
        return school;
    }

    protected Map<String, Subject> insertSubjects(String... subjectNames) {
        Map<String, Subject> subjects = new HashMap<>();
        for (String name : subjectNames) {
            subjects.put(name, insertSubject(name));
        }
        return subjects;
    }

    protected Group insertGroup(School school, String name, int year) {
        Group group = new Group(name + +year, "11");
        groupDao.insert(school, group);
        return group;
    }


    protected List<Group> insertSchoolGroups(School school, int year) {
        Group groupFrontEnd = insertGroup(school, "FrontEnd", year);
        Group groupBackEnd = insertGroup(school, "BackEnd", year);
        List<Group> groups = new ArrayList<>();
        groups.add(groupFrontEnd);
        groups.add(groupBackEnd);

        return groups;
    }

    protected List<Group> insertSchoolGroupsWithSubjects(School school, int year, Map<String, Subject> subjects) {
        Group groupFrontEnd = insertFrontEndGroupWithSubjects(school, year, subjects);
        Group groupBackEnd = insertBackEndGroupWithSubjects(school, year, subjects);
        List<Group> groups = new ArrayList<>();
        groups.add(groupFrontEnd);
        groups.add(groupBackEnd);

        return groups;
    }


    protected Group insertFrontEndGroupWithSubjects(School school, int year, Map<String, Subject> subjects) {
        Group groupFrontEnd = new Group("Frontend " + +year, "11");
        groupFrontEnd.addSubject(subjects.get("Linux"));
        groupFrontEnd.addSubject(subjects.get("NodeJS"));
        groupDao.insert(school, groupFrontEnd);
        groupDao.addSubjectToGroup(groupFrontEnd, subjects.get("Linux"));
        groupDao.addSubjectToGroup(groupFrontEnd, subjects.get("NodeJS"));
        return groupFrontEnd;
    }

    protected Group insertBackEndGroupWithSubjects(School school, int year, Map<String, Subject> subjects) {
        Group groupBackEnd = new Group("Backend " + year, "12");
        groupBackEnd.addSubject(subjects.get("Linux"));
        groupBackEnd.addSubject(subjects.get("MySQL"));
        groupDao.insert(school, groupBackEnd);
        groupDao.addSubjectToGroup(groupBackEnd, subjects.get("Linux"));
        groupDao.addSubjectToGroup(groupBackEnd, subjects.get("MySQL"));
        return groupBackEnd;
    }

    protected void insertBackendTrainees(Group groupBackEnd) {
        Trainee traineeSidorov = new Trainee("Сидор", "Сидоров", 2);
        Trainee traineeSmirnov = new Trainee("Николай", "Смирнов", 3);
        traineeDao.insert(groupBackEnd, traineeSidorov);
        traineeDao.insert(groupBackEnd, traineeSmirnov);
        groupBackEnd.addTrainee(traineeSidorov);
        groupBackEnd.addTrainee(traineeSmirnov);
    }

    protected void insertFrontEndTrainees(Group groupFrontEnd) {
        Trainee traineeIvanov = new Trainee("Иван", "Иванов", 5);
        Trainee traineePetrov = new Trainee("Петр", "Петров", 4);
        traineeDao.insert(groupFrontEnd, traineeIvanov);
        traineeDao.insert(groupFrontEnd, traineePetrov);
        groupFrontEnd.addTrainee(traineeIvanov);
        groupFrontEnd.addTrainee(traineePetrov);
    }
}

package net.thumbtack.school.database.mybatis;

import net.thumbtack.school.database.model.Subject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSubjectOperations extends TestBase {

    @Test
    public void testInsertSubject() {
        Subject subject = insertSubject("Linux");
        Subject subjectFromDB = subjectDao.getById(subject.getId());
        assertEquals(subject, subjectFromDB);
    }

    @Test
    public void testInsertSubjectWithNullName() {
        assertThrows(RuntimeException.class, () -> {
            Subject subject = new Subject(null);
            subjectDao.insert(subject);
        });
    }

    @Test
    public void testUpdateSubject() {
        Subject subject = insertSubject("Linux");
        Subject subjectFromDB = subjectDao.getById(subject.getId());
        assertEquals(subject, subjectFromDB);
        subject.setName("Windows");
        subjectDao.update(subject);
        subjectFromDB = subjectDao.getById(subject.getId());
        assertEquals(subject, subjectFromDB);
    }

    @Test
    public void testUpdateSubjectSetNullName() {
        assertThrows(RuntimeException.class, () -> {
            Subject subject = insertSubject("Linux");
            Subject subjectFromDB = subjectDao.getById(subject.getId());
            assertEquals(subject, subjectFromDB);
            subject.setName(null);
            subjectDao.update(subject);
        });
    }

    @Test
    public void testDeleteSubject() {
        Subject subject = insertSubject("Linux");
        Subject subjectFromDB = subjectDao.getById(subject.getId());
        assertEquals(subject, subjectFromDB);
        subjectDao.delete(subject);
        subjectFromDB = subjectDao.getById(subject.getId());
        assertNull(subjectFromDB);
    }

    @Test
    public void testInsertTwoSubjects() {
        Subject subjectLinux = insertSubject("Linux");
        Subject subjectMySQL = insertSubject("MySQL");
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subjectLinux);
        subjects.add(subjectMySQL);
        List<Subject> subjectsFromDB = subjectDao.getAll();
        assertEquals(subjects, subjectsFromDB);
    }

}

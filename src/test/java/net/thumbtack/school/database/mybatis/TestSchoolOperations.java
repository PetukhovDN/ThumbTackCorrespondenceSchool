package net.thumbtack.school.database.mybatis;

import net.thumbtack.school.database.model.School;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSchoolOperations extends TestBase {
    @Test
    public void testInsertSchool() {
        School school2018 = insertTTSchool("TTSchool", 2018);
        School schoolFromDB = schoolDao.getById(school2018.getId());
        assertEquals(school2018, schoolFromDB);
    }

    @Test
    public void testInsertSchoolWithNullName() {
        assertThrows(RuntimeException.class, () -> {
            School school = new School(null, 2018);
            schoolDao.insert(school);
        });
    }


    @Test
    public void testUpdateSchool() {
        School school = insertTTSchool("TTSchool", 2018);
        School schoolFromDB = schoolDao.getById(school.getId());
        assertEquals(school, schoolFromDB);
        school.setName("Школа ТТ");
        school.setYear(2019);
        schoolDao.update(school);
        schoolFromDB = schoolDao.getById(school.getId());
        assertEquals(school, schoolFromDB);
    }

    @Test
    public void testChangeSchoolNameToNull() {
        assertThrows(RuntimeException.class, () -> {
            School school2018 = insertTTSchool("TTSchool", 2018);
            assertNotEquals(0, school2018.getId());
            School schoolFromDB = schoolDao.getById(school2018.getId());
            assertEquals(school2018, schoolFromDB);
            school2018.setName(null);
            schoolDao.update(school2018);
        });
    }

    @Test
    public void testDeleteSchool() {
        School school2018 = insertTTSchool("TTSchool", 2018);
        School schoolFromDB = schoolDao.getById(school2018.getId());
        assertEquals(school2018, schoolFromDB);
        schoolDao.delete(school2018);
        schoolFromDB = schoolDao.getById(school2018.getId());
        assertNull(schoolFromDB);
    }

    @Test
    public void testInsertTwoSchools() {
        School school2018 = insertTTSchool("TTSchool", 2018);
        School school2019 = insertTTSchool("TTSchool", 2019);
        List<School> schoolsFromDB = schoolDao.getAllLazy();
        assertEquals(2, schoolsFromDB.size());
        schoolsFromDB.sort(Comparator.comparingInt(School::getId));
        assertEquals(school2018, schoolsFromDB.get(0));
        assertEquals(school2019, schoolsFromDB.get(1));
    }

    @Test
    public void testInsertTwoSchoolsWithSameNameAndYear() {
        assertThrows(RuntimeException.class, () -> {
            School school20181 = insertTTSchool("TTSchool", 2018);
            School school20182 = insertTTSchool("TTSchool", 2018);
        });
    }


}

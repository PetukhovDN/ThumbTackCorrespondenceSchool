package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JdbcUtils.class)
public class TestJdbcCalls {

    private static Connection spyConnection;
    private static final String USER = "test";
    private static final String PASSWORD = "test";
    private static final String URL = "jdbc:mysql://localhost:3306/ttschool?useUnicode=yes&characterEncoding=UTF8&useSSL=false&serverTimezone=Asia/Omsk";

    @BeforeClass()
    public static void setUp() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        spyConnection = spy(connection);
        PowerMockito.mockStatic(JdbcUtils.class);
        when(JdbcUtils.getConnection()).thenReturn(spyConnection);
    }

    @Before()
    public void clearDatabase() throws SQLException {
        JdbcService.deleteSchools();
        JdbcService.deleteTrainees();
        JdbcService.deleteSubjects();
    }

    @Test
    public void testStatementsNumber() throws SQLException {
        School school = new School("TTSchool", 2018);
        JdbcService.insertSchool(school);
        Group groupFrontEnd = new Group("Frontend", "11");
        Group groupBackEnd = new Group("Backend", "12");
        JdbcService.insertGroup(school, groupFrontEnd);
        JdbcService.insertGroup(school, groupBackEnd);

        reset(spyConnection);
        JdbcService.getSchoolByIdWithGroups(school.getId());
        verify(spyConnection, never()).createStatement();
        verify(spyConnection).prepareStatement(anyString());

        reset(spyConnection);
        JdbcService.getSchoolsWithGroups();
        verify(spyConnection, never()).createStatement();
        verify(spyConnection).prepareStatement(anyString());
    }
}



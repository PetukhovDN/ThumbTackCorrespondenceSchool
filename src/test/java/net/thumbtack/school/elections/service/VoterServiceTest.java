package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.dto.request.RegisterVoterRequest;
import net.thumbtack.school.elections.enums.ResultsOfRequests;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Voter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class VoterServiceTest {

    private Voter firstVoter;
    private Voter secondVoter;
    private Voter sameAsFirstVoter;

    @Mock
    private VoterDao voterDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        firstVoter = new Voter(new RegisterVoterRequest("Bob", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty")); // REVU Очень длинные строки
        secondVoter = new Voter(new RegisterVoterRequest("Tom", "Poker", "Petrovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"));
        sameAsFirstVoter = new Voter(new RegisterVoterRequest("Bob", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"));
    }

    @Test
    void registerVoter() throws ElectionsException {
        //работа с первым избирателем, проверка что ответ вернулся и не null, проверка что метод отработал
        when(voterDao.insertToDataBase(firstVoter)).thenReturn(firstVoter.getToken());
        UUID firstVoterResultToken = voterDao.insertToDataBase(firstVoter);
        assertNotNull(firstVoterResultToken);
        assertEquals(firstVoter.getToken(), firstVoterResultToken);
        verify(voterDao).insertToDataBase(firstVoter);

        //работа со вторым избирателем, проверка что ответ вернулся и не null, проверка что метод отработал
        when(voterDao.insertToDataBase(secondVoter)).thenReturn(secondVoter.getToken());
        UUID secondVoterResultToken = voterDao.insertToDataBase(secondVoter);
        assertNotNull(secondVoterResultToken);
        assertEquals(secondVoter.getToken(), secondVoterResultToken);
        verify(voterDao).insertToDataBase(secondVoter);
    }

    @Test
    void registerVoter_Throws_Exception() throws ElectionsException {
        //работа с третьим избирателем (идентичным первому), проверка что исключение было выброшено, проверка что метод отработал
        when(voterDao.insertToDataBase(sameAsFirstVoter)).thenThrow(ElectionsException.class);
        ElectionsException thrown = assertThrows(ElectionsException.class, () -> voterDao.insertToDataBase(sameAsFirstVoter));
        assertNotEquals("", thrown.getExceptionErrorInfo());
        verify(voterDao).insertToDataBase(sameAsFirstVoter);
    }

    @Test
    void logoutVoter() throws ElectionsException {
        when(voterDao.logoutFromDatabase(firstVoter.getToken())).thenReturn(ResultsOfRequests.SUCCESSFUL_REQUEST);
        ResultsOfRequests resultOfLogout = voterDao.logoutFromDatabase(firstVoter.getToken());
        assertNotNull(resultOfLogout);
        assertEquals(ResultsOfRequests.SUCCESSFUL_REQUEST, resultOfLogout);
        verify(voterDao).logoutFromDatabase(firstVoter.getToken());
    }

    @Test
    void loginVoter() throws ElectionsException {
        when(voterDao.loginToDatabase(firstVoter.getFirstName(), firstVoter.getLastName())).thenReturn(firstVoter.getToken());
        UUID firstVoterResultToken = voterDao.loginToDatabase(firstVoter.getFirstName(), firstVoter.getLastName());
        assertNotNull(firstVoterResultToken);
        assertEquals(firstVoter.getToken(), firstVoterResultToken);
        verify(voterDao).loginToDatabase(firstVoter.getFirstName(), firstVoter.getLastName());
    }

    @Test
    void getAllVoters() throws ElectionsException {
        ArrayList<Voter> votersList = new ArrayList<>();
        when(voterDao.getAllVotersFromDatabase(firstVoter.getToken())).thenReturn(votersList);
        List<Voter> firstVoterResultToken = voterDao.getAllVotersFromDatabase(firstVoter.getToken());
        assertNotNull(firstVoterResultToken);
        assertEquals(votersList, firstVoterResultToken);
        verify(voterDao).getAllVotersFromDatabase(firstVoter.getToken());
    }
}

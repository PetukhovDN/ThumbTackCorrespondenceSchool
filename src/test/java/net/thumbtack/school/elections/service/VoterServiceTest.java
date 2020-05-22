package net.thumbtack.school.elections.service;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.model.Voter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class VoterServiceTest {

    private VoterService voterService;
    private RegisterVoterDtoRequest firstVoterRequest;
    private RegisterVoterDtoRequest secondVoterRequest;
    private RegisterVoterDtoRequest sameAsFirstVoterRequest;
    private Voter firstVoter;
    private Voter secondVoter;
    private Voter sameAsFirstVoter;

    @Mock
    private VoterDao voterDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        voterService = new VoterService();
        firstVoterRequest = new RegisterVoterDtoRequest("Bob", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        secondVoterRequest = new RegisterVoterDtoRequest("Tom", "Poker", "Petrovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        sameAsFirstVoterRequest = new RegisterVoterDtoRequest("Bob", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        firstVoter = new Voter(firstVoterRequest);
        secondVoter = new Voter(secondVoterRequest);
        sameAsFirstVoter = new Voter(sameAsFirstVoterRequest);
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
        assertNotEquals("", thrown.getErrorCode());
        verify(voterDao).insertToDataBase(sameAsFirstVoter);
    }

    @Test
    void logoutVoter() throws ElectionsException {
    }

    @Test
    void loginVoter() throws ElectionsException {
    }

    @Test
    void getAllVoters() {
    }
}
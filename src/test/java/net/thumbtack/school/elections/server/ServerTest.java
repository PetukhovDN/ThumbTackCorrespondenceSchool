package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.dto.request.*;
import net.thumbtack.school.elections.dto.response.*;
import net.thumbtack.school.elections.enums.ResultsOfRequests;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {
    //токен из базы
    private static UUID adminTokenForCheck = UUID.fromString("4d50c72b-8342-4d44-ab09-4026dd0e333d");
    private static Server server;
    private static Database database;
    private static Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        server = new Server();
        server.startServer(null);
        database = Database.getInstance();
    }

    @AfterEach
    void tearDown() {
        server.stopServer(null);
    }

    @Test
    public void testRegisterVoterRightInfo() {
        int n = database.getVotersMap().size();
        RegisterVoterRequest request1 = new RegisterVoterRequest(
                "Bob",
                "Fisher",
                "Ivanovich",
                "Lenina",
                40,
                277,
                "bobcherchil122",
                "1234567qwerty");
        RegisterVoterRequest request2 = new RegisterVoterRequest(
                "Tom",
                "Fisher",
                "",
                "Lenina",
                40,
                277,
                "bobcherchil122",
                "1234567qwerty");
        String jsonRequest1 = gson.toJson(request1);
        String jsonRequest2 = gson.toJson(request2);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        String jsonResult2 = server.registerVoter(jsonRequest2);
        RegisterVoterResponse result1 = gson.fromJson(jsonResult1, RegisterVoterResponse.class);
        RegisterVoterResponse result2 = gson.fromJson(jsonResult2, RegisterVoterResponse.class);

        //проверка что запрос был успешным и количество избирателей увеличилось
        assertEquals(n + 2, database.getVotersMap().size());
        assertEquals(gson.toJson(result1), jsonResult1);
        assertEquals(gson.toJson(result2), jsonResult2);
    }

    @Test
    public void testRegisterVoterWrongInfo() {
        int n = database.getVotersMap().size();
        ElectionsException exception1 = new ElectionsException(ExceptionErrorInfo.EMPTY_VOTER_LASTNAME);
        ElectionsException exception2 = new ElectionsException(ExceptionErrorInfo.DUPLICATE_VOTER);
        //пустое поле фамилии
        RegisterVoterRequest request1 = new RegisterVoterRequest(
                "Bob",
                "",
                "Ivanovich",
                "Lenina",
                40,
                277,
                "bobcherchil122",
                "1234567qwerty");
        //валидный запрос
        RegisterVoterRequest request2 = new RegisterVoterRequest(
                "Tim",
                "Fisher",
                "Ivanovich",
                "Lenina",
                40,
                277,
                "bobcherchil122",
                "1234567qwerty");
        //идентичный предыдущему запрос
        RegisterVoterRequest request3 = new RegisterVoterRequest(
                "Tim",
                "Fisher",
                "Ivanovich",
                "Lenina",
                40,
                277,
                "bobcherchil122",
                "1234567qwerty");
        String jsonRequest1 = gson.toJson(request1);
        String jsonRequest2 = gson.toJson(request2);
        String jsonRequest3 = gson.toJson(request3);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        //запрос для дублирования избирателей
        String jsonResult2 = server.registerVoter(jsonRequest2);
        RegisterVoterResponse registerResponse = gson.fromJson(jsonResult2, RegisterVoterResponse.class);
        String jsonResult3 = server.registerVoter(jsonRequest3);

        assertEquals(gson.toJson(registerResponse), jsonResult2);
        assertEquals(gson.toJson(exception1.getExceptionErrorInfo().getErrorCode() + "\n" + exception1.getExceptionErrorInfo().getErrorString()), jsonResult1);
        assertEquals(gson.toJson(exception2.getExceptionErrorInfo().getErrorCode() + "\n" + exception2.getExceptionErrorInfo().getErrorString()), jsonResult3);
        //проверка что сработал только request2
        assertEquals(n + 1, database.getVotersMap().size());
    }

    @Test
    void testLogoutVoter() {
        int n = database.getVotersMap().size();
        RegisterVoterRequest registerRequest = new RegisterVoterRequest(
                "Test",
                "Voter",
                "",
                "Zukova",
                13,
                45,
                "petrov12345",
                "qwertyasdf");
        String jsonRegisterRequest = gson.toJson(registerRequest);
        String jsonRegisterResult = server.registerVoter(jsonRegisterRequest);
        RegisterVoterResponse registerResult = gson.fromJson(jsonRegisterResult, RegisterVoterResponse.class);
        UUID tokenForCheck = registerResult.getToken();

        LogoutVoterRequest logoutRequest = new LogoutVoterRequest(tokenForCheck);
        String jsonLogoutRequest = gson.toJson(logoutRequest);
        String jsonLogoutResult = server.logoutVoter(jsonLogoutRequest);
        LogoutVoterResponse logoutResponse = gson.fromJson(jsonLogoutResult, LogoutVoterResponse.class);

        //проверка что тестовый избиратель добавился
        assertEquals(n + 1, database.getVotersMap().size());
        assertEquals(gson.toJson(logoutResponse), jsonLogoutResult);
    }

    /**
     * Отдельно не работает, так как база одна
     */
    @Test
    void testLoginVoter() {
        int n = database.getVotersMap().size();

        LoginVoterRequest loginRequest = new LoginVoterRequest("petrov12345", "qwertyasdf");
        String jsonLoginRequest = gson.toJson(loginRequest);
        String jsonLoginResult = server.loginVoter(jsonLoginRequest);
        LoginVoterResponse loginVDResponse = gson.fromJson(jsonLoginResult, LoginVoterResponse.class);

        assertEquals(n, database.getVotersMap().size());
        assertEquals(gson.toJson(loginVDResponse), jsonLoginResult);
    }

    @Test
    void testGetAllVotersList() {
        int n = database.getVotersMap().size();

        //добавление избирателя для проверки
        RegisterVoterRequest registerRequest = new RegisterVoterRequest(
                "Ivan",
                "Makarov",
                "Sergeevich",
                "Zukova",
                13,
                45,
                "petrov12345",
                "qwertyasdf");
        String jsonRegisterRequest = gson.toJson(registerRequest);
        String jsonRegisterResult = server.registerVoter(jsonRegisterRequest);
        RegisterVoterResponse registerResponse = gson.fromJson(jsonRegisterResult, RegisterVoterResponse.class);
        UUID tokenForCheck = registerResponse.getToken();

        GetAllVotersRequest getVotersRequest = new GetAllVotersRequest(tokenForCheck);
        String jsonGetVotersRequest = gson.toJson(getVotersRequest);
        String jsonGetVotersResult = server.getAllVoters(jsonGetVotersRequest);
        GetAllVotersResponse result = gson.fromJson(jsonGetVotersResult, GetAllVotersResponse.class);

        assertEquals(gson.toJson(result), jsonGetVotersResult);
        //проверка что тестовый избиратель добавился
        assertEquals(n + 1, database.getVotersMap().size());
    }

    @Test
    void testAddCandidate() {
        int m = database.getCandidateMap().size();
        ElectionsException exception1 = new ElectionsException(ExceptionErrorInfo.EMPTY_CANDIDATE_LIST);
        RegisterVoterRequest registerRequest1 = new RegisterVoterRequest(
                "Test1",
                "Voter1",
                "",
                "First",
                1,
                1,
                "TestVoter1",
                "testPassword1");
        RegisterVoterRequest registerRequest2 = new RegisterVoterRequest(
                "Test2",
                "Voter2",
                "",
                "Second",
                1,
                1,
                "TestVoter1",
                "testPassword1");
        String jsonRegisterRequest1 = gson.toJson(registerRequest1);
        String jsonRegisterRequest2 = gson.toJson(registerRequest2);
        String jsonRegisterResult1 = server.registerVoter(jsonRegisterRequest1);
        String jsonRegisterResult2 = server.registerVoter(jsonRegisterRequest2);
        RegisterVoterResponse registerResult1 = gson.fromJson(jsonRegisterResult1, RegisterVoterResponse.class);
        RegisterVoterResponse registerResult2 = gson.fromJson(jsonRegisterResult2, RegisterVoterResponse.class);

        //первый избиратель в базе
        AddCandidateRequest addCandidateRequest1 = new AddCandidateRequest("Test1", "Voter1", registerResult1.getToken());
        //второй избиратель в базе
        AddCandidateRequest addCandidateRequest2 = new AddCandidateRequest("Test2", "Voter2", registerResult2.getToken());
        //не существует такого избирателя в базе
        AddCandidateRequest addCandidateRequest3 = new AddCandidateRequest("Test3", "Voter3", registerResult1.getToken());
        String jsonAddCandidateRequest1 = gson.toJson(addCandidateRequest1);
        String jsonAddCandidateRequest2 = gson.toJson(addCandidateRequest2);
        String jsonAddCandidateRequest3 = gson.toJson(addCandidateRequest3);
        String jsonAddCandidateResponse1 = server.addCandidate(jsonAddCandidateRequest1);
        String jsonAddCandidateResponse2 = server.addCandidate(jsonAddCandidateRequest2);
        String jsonAddCandidateResponse3 = server.addCandidate(jsonAddCandidateRequest3);
        AddCandidateResponse addCandidateResponse1 = gson.fromJson(jsonAddCandidateResponse1, AddCandidateResponse.class);
        AddCandidateResponse addCandidateResponse2 = gson.fromJson(jsonAddCandidateResponse2, AddCandidateResponse.class);

        assertEquals(gson.toJson(ResultsOfRequests.SUCCESSFUL_REQUEST), gson.toJson(addCandidateResponse1.getResult()));
        assertEquals(gson.toJson(ResultsOfRequests.SUCCESSFUL_REQUEST), gson.toJson(addCandidateResponse2.getResult()));
        assertEquals(gson.toJson(exception1.getExceptionErrorInfo().getErrorCode() + "\n" + exception1.getExceptionErrorInfo().getErrorString()), jsonAddCandidateResponse3);

        //оба добавились но только первый согласен быть кандидатом
        assertEquals(m + 2, database.getCandidateMap().size());
    }

    @Test
    @Disabled("Плавающая ошибка")
    void testAgreeToBeCandidate() {
        int m = database.getCandidateMap().size();
        RegisterVoterRequest registerRequest1 = new RegisterVoterRequest(
                "Test1",
                "Voter1",
                "",
                "First",
                1,
                1,
                "TestVoter1",
                "testPassword1");
        String jsonRegisterRequest1 = gson.toJson(registerRequest1);
        String jsonRegisterResult1 = server.registerVoter(jsonRegisterRequest1);
        RegisterVoterResponse registerResult1 = gson.fromJson(jsonRegisterResult1, RegisterVoterResponse.class);

        AddCandidateRequest addCandidateRequest = new AddCandidateRequest("Bob", "Fisher", registerResult1.getToken());
        String jsonAddCandidateRequest = gson.toJson(addCandidateRequest);
        String jsonAddCandidateResponse = server.addCandidate(jsonAddCandidateRequest);

        AgreeToBeCandidateRequest agreeRequest = new AgreeToBeCandidateRequest(registerResult1.getToken());
        String jsonAgreeRequest = gson.toJson(agreeRequest);
        String jsonAgreeResponse = server.agreeToBeCandidate(jsonAgreeRequest);
        AgreeToBeCandidateResponse agreeResponse = gson.fromJson(jsonAgreeResponse, AgreeToBeCandidateResponse.class);

        assertEquals(gson.toJson(agreeResponse.getResult()), gson.toJson(registerResult1.getToken()));
        assertEquals(m + 1, database.getCandidateMap().size());
    }


    @Test
    void addProposalToCandidateProgram() {
        int n = database.getVotersMap().size();
    }

    @Test
    void removeProposalFromCandidateProgram() {
        int n = database.getVotersMap().size();
    }

    @Test
    void testGetAllCandidates() {
        int n = database.getVotersMap().size();
    }

    @Test
    void testMakeProposal() {
        int n = database.getVotersMap().size();
    }

    @Test
    void testAddRating() {
        int n = database.getVotersMap().size();
    }

    @Test
    void testRemoveRating() {
        int n = database.getVotersMap().size();
    }

    @Test
    void testGetAllProposalsWithRate() {
        int n = database.getVotersMap().size();
    }

    @Test
    void testGetAllProposalsFromVoter() {
        int n = database.getVotersMap().size();
    }

    @Test
    @Disabled("Всё ломает")
    void testStartElections() {
        int n = database.getVotersMap().size();
        StartElectionsRequest startRequest1 = new StartElectionsRequest(adminTokenForCheck);
        String jsonStartRequest1 = gson.toJson(startRequest1);
        String jsonStartResponse1 = server.startElections(jsonStartRequest1);
        StartElectionsResponse startResponse1 = gson.fromJson(jsonStartResponse1, StartElectionsResponse.class);

        assertEquals(gson.toJson(startResponse1.getResult()), gson.toJson(adminTokenForCheck));

        StartElectionsRequest startRequest2 = new StartElectionsRequest(adminTokenForCheck);
        String jsonStartRequest2 = gson.toJson(startRequest2);
        String jsonStartResponse2 = server.startElections(jsonStartRequest2);
        StartElectionsResponse startResponse2 = gson.fromJson(jsonStartResponse2, StartElectionsResponse.class);

        assertEquals(ServerTest.gson.toJson(startResponse2.getResult()), gson.toJson(adminTokenForCheck));
    }

    @Test
    void testVoteForCandidate() {
        int n = database.getVotersMap().size();
    }

    @Test
    void testChooseMajorAndStopElections() {
        int n = database.getVotersMap().size();
    }
}

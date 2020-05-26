package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.dto.request.*;
import net.thumbtack.school.elections.dto.response.*;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Voter;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {
    private static UUID adminTokenForCheck = UUID.fromString("4d50c72b-8342-4d44-ab09-4026dd0e333d"); //токен из базы
    Server server;
    Database database;

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
        RegisterVoterRequest request1 = new RegisterVoterRequest("Bob", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        RegisterVoterRequest request2 = new RegisterVoterRequest("Tom", "Fisher", "", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        String jsonResult2 = server.registerVoter(jsonRequest2);
        RegisterVoterResponse result1 = new Gson().fromJson(jsonResult1, RegisterVoterResponse.class);
        RegisterVoterResponse result2 = new Gson().fromJson(jsonResult2, RegisterVoterResponse.class);

        assertEquals(n + 2, database.getVotersMap().size()); //проверка что запрос был успешным и количество избирателей увеличилось
        assertEquals(new Gson().toJson(result1), jsonResult1);
        assertEquals(new Gson().toJson(result2), jsonResult2);
    }

    @Test
    public void testRegisterVoterWrongInfo() {
        int n = database.getVotersMap().size();
        ElectionsException exception1 = new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_LASTNAME);
        ElectionsException exception2 = new ElectionsException(ExceptionErrorCode.DUPLICATE_VOTER);
        RegisterVoterRequest request1 = new RegisterVoterRequest("Bob", "", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //пустое поле фамилии // REVU Очень длинные строки
        RegisterVoterRequest request2 = new RegisterVoterRequest("Tim", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //валидный запрос
        RegisterVoterRequest request3 = new RegisterVoterRequest("Tim", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //идентичный предыдущему запрос
        String jsonRequest1 = new Gson().toJson(request1); // REVU Вынесите new Gson() в поле тестового класса
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonRequest3 = new Gson().toJson(request3);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        String jsonResult2 = server.registerVoter(jsonRequest2); //запрос для дублирования избирателей
        RegisterVoterResponse registerResponse = new Gson().fromJson(jsonResult2, RegisterVoterResponse.class);
        String jsonResult3 = server.registerVoter(jsonRequest3);

        assertEquals(new Gson().toJson(registerResponse), jsonResult2);
        assertEquals(new Gson().toJson(exception1.getErrorCode().getErrorString()), jsonResult1);
        assertEquals(new Gson().toJson(exception2.getErrorCode().getErrorString()), jsonResult3);
        assertEquals(n + 1, database.getVotersMap().size()); //проверка что сработал только request2
    }

    @Test
    void testLogoutVoter() {
        int n = database.getVotersMap().size();
        RegisterVoterRequest registerRequest = new RegisterVoterRequest("Andrey", "Petrov", "Sergeevich", "Zukova", 13, 45, "petrov12345", "qwertyasdf");
        String jsonRegisterRequest = new Gson().toJson(registerRequest);
        String jsonRegisterResult = server.registerVoter(jsonRegisterRequest);
        RegisterVoterResponse registerResult = new Gson().fromJson(jsonRegisterResult, RegisterVoterResponse.class);
        UUID tokenForCheck = registerResult.getToken();

        LogoutVoterRequest logoutRequest = new LogoutVoterRequest(tokenForCheck);
        String jsonLogoutRequest = new Gson().toJson(logoutRequest);
        String jsonLogoutResult = server.logoutVoter(jsonLogoutRequest);
        LogoutVoterResponse logoutResponse = new Gson().fromJson(jsonLogoutResult, LogoutVoterResponse.class);

        assertEquals(n + 1, database.getVotersMap().size()); //проверка что тестовый избиратель добавился
        assertEquals(new Gson().toJson(logoutResponse), jsonLogoutResult);
    }

    /**
     * Отдельно не работает, так как база одна
     */
    @Test
    void testLoginVoter() {
        int n = database.getVotersMap().size();
        Voter testVoter = (Voter) database.getVotersMap().values().toArray()[0]; // REVU Тесты должны быть независимыми. Зарегистрируйте пользователя, если он необходим для теста. Или используйте заранее зарегистрированного из файла.

        LoginVoterRequest loginRequest = new LoginVoterRequest(testVoter.getLogin(), testVoter.getPassword());
        String jsonLoginRequest = new Gson().toJson(loginRequest);
        String jsonLoginResult = server.loginVoter(jsonLoginRequest);
        LoginVoterResponse loginVDResponse = new Gson().fromJson(jsonLoginResult, LoginVoterResponse.class);

        assertEquals(n, database.getVotersMap().size());
        assertEquals(new Gson().toJson(loginVDResponse), jsonLoginResult);
    }

    @Test
    void testGetAllVotersList() {
        int n = database.getVotersMap().size();

        RegisterVoterRequest registerRequest = new RegisterVoterRequest("Ivan", "Makarov", "Sergeevich", "Zukova", 13, 45, "petrov12345", "qwertyasdf");
        String jsonRegisterRequest = new Gson().toJson(registerRequest);
        String jsonRegisterResult = server.registerVoter(jsonRegisterRequest);
        RegisterVoterResponse registerResponse = new Gson().fromJson(jsonRegisterResult, RegisterVoterResponse.class); //добавление избирателя для проверки
        UUID tokenForCheck = registerResponse.getToken();
        GetAllVotersRequest getVotersRequest = new GetAllVotersRequest(tokenForCheck);
        String jsonGetVotersRequest = new Gson().toJson(getVotersRequest);
        String jsonGetVotersResult = server.getAllVoters(jsonGetVotersRequest);
        GetAllVotersResponse result = new Gson().fromJson(jsonGetVotersResult, GetAllVotersResponse.class);

        assertEquals(new Gson().toJson(result), jsonGetVotersResult);
        assertEquals(n + 1, database.getVotersMap().size()); //проверка что тестовый избиратель добавился
    }

    @Test
    void testAddCandidate() {
        int m = database.getCandidateMap().size();
        UUID tokenForCheck1 = (UUID) database.getValidTokens().toArray()[0];
        UUID tokenForCheck2 = (UUID) database.getValidTokens().toArray()[1];
        ElectionsException exception1 = new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_LIST);

        AddCandidateRequest addCandidateRequest1 = new AddCandidateRequest("Bob", "Fisher", tokenForCheck1);  //первый избиратель в базе
        AddCandidateRequest addCandidateRequest2 = new AddCandidateRequest("Tim", "Fisher", tokenForCheck2);  //второй избиратель в базе
        AddCandidateRequest addCandidateRequest3 = new AddCandidateRequest("Bill", "Fisher", tokenForCheck1); //не существует такого избирателя в базе
        String jsonAddCandidateRequest1 = new Gson().toJson(addCandidateRequest1);
        String jsonAddCandidateRequest2 = new Gson().toJson(addCandidateRequest2);
        String jsonAddCandidateRequest3 = new Gson().toJson(addCandidateRequest3);
        String jsonAddCandidateResponse1 = server.addCandidate(jsonAddCandidateRequest1);
        String jsonAddCandidateResponse2 = server.addCandidate(jsonAddCandidateRequest2);
        String jsonAddCandidateResponse3 = server.addCandidate(jsonAddCandidateRequest3);
        AddCandidateResponse addCandidateResponse1 = new Gson().fromJson(jsonAddCandidateResponse1, AddCandidateResponse.class);
        AddCandidateResponse addCandidateResponse2 = new Gson().fromJson(jsonAddCandidateResponse2, AddCandidateResponse.class);

        assertEquals(new Gson().toJson(addCandidateResponse1.getToken()), new Gson().toJson(tokenForCheck1));
        assertEquals(new Gson().toJson(addCandidateResponse2.getToken()), new Gson().toJson(tokenForCheck2));
        assertEquals(new Gson().toJson(exception1.getErrorCode().getErrorString()), jsonAddCandidateResponse3);

        assertEquals(m + 2, database.getCandidateMap().size()); // оба добавились но только первый согласен быть кандидатом
    }

    @Test
    @Disabled("Плавающая ошибка")
    void testAgreeToBeCandidate() {
        int m = database.getCandidateMap().size(); //0
        UUID tokenForCheck = (UUID) database.getValidTokens().toArray()[1];  //токен Tim Fisher

        AddCandidateRequest addCandidateRequest = new AddCandidateRequest("Bob", "Fisher", tokenForCheck);
        String jsonAddCandidateRequest = new Gson().toJson(addCandidateRequest);
        String jsonAddCandidateResponse = server.addCandidate(jsonAddCandidateRequest);

        AgreeToBeCandidateRequest agreeRequest = new AgreeToBeCandidateRequest(tokenForCheck);
        String jsonAgreeRequest = new Gson().toJson(agreeRequest);
        String jsonAgreeResponse = server.agreeToBeCandidate(jsonAgreeRequest);
        AgreeToBeCandidateResponse agreeResponse = new Gson().fromJson(jsonAgreeResponse, AgreeToBeCandidateResponse.class);

        assertEquals(new Gson().toJson(agreeResponse.getToken()), new Gson().toJson(tokenForCheck));
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

    @Ignore
    void testStartElections() {
        int n = database.getVotersMap().size();
        StartElectionsRequest startRequest1 = new StartElectionsRequest(adminTokenForCheck);
        String jsonStartRequest1 = new Gson().toJson(startRequest1);
        String jsonStartResponse1 = server.startElections(jsonStartRequest1);
        StartElectionsResponse startResponse1 = new Gson().fromJson(jsonStartResponse1, StartElectionsResponse.class);

        assertEquals(new Gson().toJson(startResponse1.getToken()), new Gson().toJson(adminTokenForCheck));

        StartElectionsRequest startRequest2 = new StartElectionsRequest(adminTokenForCheck);
        String jsonStartRequest2 = new Gson().toJson(startRequest2);
        String jsonStartResponse2 = server.startElections(jsonStartRequest2);
        StartElectionsResponse startResponse2 = new Gson().fromJson(jsonStartResponse2, StartElectionsResponse.class);

        assertEquals(new Gson().toJson(startResponse2.getToken()), new Gson().toJson(adminTokenForCheck));
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

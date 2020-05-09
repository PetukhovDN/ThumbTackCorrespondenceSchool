package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.dto.request.*;
import net.thumbtack.school.elections.dto.response.*;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Voter;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {
    private static UUID adminTokenForCheck = UUID.fromString("4d50c72b-8342-4d44-ab09-4026dd0e333d"); //токен из базы

    @Test
    public void testRegisterVoterRightInfo() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Bob", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Tom", "Fisher", "", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        String jsonResult2 = server.registerVoter(jsonRequest2);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResult1, RegisterVoterDtoResponse.class);
        RegisterVoterDtoResponse result2 = new Gson().fromJson(jsonResult2, RegisterVoterDtoResponse.class);

        assertEquals(n + 2, Database.getInstance().getVotersMap().size()); //проверка что запрос был успешным и количество избирателей увеличилось
        assertEquals(new Gson().toJson(result1), jsonResult1);
        assertEquals(new Gson().toJson(result2), jsonResult2);

        server.stopServer(null);
    }

    @Test
    public void testRegisterVoterWrongInfo() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();
        ElectionsException exception1 = new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_LASTNAME);
        ElectionsException exception2 = new ElectionsException(ExceptionErrorCode.DUPLICATE_VOTER);
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Bob", "", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //пустое поле фамилии
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Tim", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //валидный запрос
        RegisterVoterDtoRequest request3 = new RegisterVoterDtoRequest("Tim", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //идентичный предыдущему запрос
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonRequest3 = new Gson().toJson(request3);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        String jsonResult2 = server.registerVoter(jsonRequest2); //запрос для дублирования избирателей
        RegisterVoterDtoResponse registerResponse = new Gson().fromJson(jsonResult2, RegisterVoterDtoResponse.class);
        String jsonResult3 = server.registerVoter(jsonRequest3);

        assertEquals(new Gson().toJson(registerResponse), jsonResult2);
        assertEquals(new Gson().toJson(exception1.getErrorCode().getErrorString()), jsonResult1);
        assertEquals(new Gson().toJson(exception2.getErrorCode().getErrorString()), jsonResult3);
        assertEquals(n + 1, Database.getInstance().getVotersMap().size()); //проверка что сработал только request2
        server.stopServer(null);
    }

    @Test
    void testLogoutVoter() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();
        RegisterVoterDtoRequest registerRequest = new RegisterVoterDtoRequest("Andrey", "Petrov", "Sergeevich", "Zukova", 13, 45, "petrov12345", "qwertyasdf");
        String jsonRegisterRequest = new Gson().toJson(registerRequest);
        String jsonRegisterResult = server.registerVoter(jsonRegisterRequest);
        RegisterVoterDtoResponse registerResult = new Gson().fromJson(jsonRegisterResult, RegisterVoterDtoResponse.class);
        UUID tokenForCheck = registerResult.getToken();

        LogoutVoterDtoRequest logoutRequest = new LogoutVoterDtoRequest(tokenForCheck);
        String jsonLogoutRequest = new Gson().toJson(logoutRequest);
        String jsonLogoutResult = server.logoutVoter(jsonLogoutRequest);
        LogoutVoterDtoResponse logoutResponse = new Gson().fromJson(jsonLogoutResult, LogoutVoterDtoResponse.class);

        assertEquals(n + 1, Database.getInstance().getVotersMap().size()); //проверка что тестовый избиратель добавился
        assertEquals(new Gson().toJson(logoutResponse), jsonLogoutResult);
        server.stopServer(null);
    }

    /**
     * Отдельно не работает, так как база одна
     */
    @Test
    void testLoginVoter() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();
        Voter testVoter = (Voter) Database.getInstance().getVotersMap().values().toArray()[0];

        LoginVoterDtoRequest loginRequest = new LoginVoterDtoRequest(testVoter.getLogin(), testVoter.getPassword());
        String jsonLoginRequest = new Gson().toJson(loginRequest);
        String jsonLoginResult = server.loginVoter(jsonLoginRequest);
        LoginVoterDtoResponse loginVDResponse = new Gson().fromJson(jsonLoginResult, LoginVoterDtoResponse.class);

        assertEquals(n, Database.getInstance().getVotersMap().size());
        assertEquals(new Gson().toJson(loginVDResponse), jsonLoginResult);
        server.stopServer(null);
    }

    @Test
    void testGetAllVotersList() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();

        RegisterVoterDtoRequest registerRequest = new RegisterVoterDtoRequest("Ivan", "Makarov", "Sergeevich", "Zukova", 13, 45, "petrov12345", "qwertyasdf");
        String jsonRegisterRequest = new Gson().toJson(registerRequest);
        String jsonRegisterResult = server.registerVoter(jsonRegisterRequest);
        RegisterVoterDtoResponse registerResponse = new Gson().fromJson(jsonRegisterResult, RegisterVoterDtoResponse.class); //добавление избирателя для проверки
        UUID tokenForCheck = registerResponse.getToken();
        GetAllVotersDtoRequest getVotersRequest = new GetAllVotersDtoRequest(tokenForCheck);
        String jsonGetVotersRequest = new Gson().toJson(getVotersRequest);
        String jsonGetVotersResult = server.getAllVoters(jsonGetVotersRequest);
        GetAllVotersDtoResponse result = new Gson().fromJson(jsonGetVotersResult, GetAllVotersDtoResponse.class);

        assertEquals(new Gson().toJson(result), jsonGetVotersResult);
        assertEquals(n + 1, Database.getInstance().getVotersMap().size()); //проверка что тестовый избиратель добавился
        server.stopServer(null);
    }

    @Test
    void testAddCandidate() {
        Server server = new Server();
        server.startServer(null);
        int m = Database.getInstance().getCandidateMap().size();
        UUID tokenForCheck1 = (UUID) Database.getInstance().getValidTokens().toArray()[0];
        UUID tokenForCheck2 = (UUID) Database.getInstance().getValidTokens().toArray()[1];
        ElectionsException exception1 = new ElectionsException(ExceptionErrorCode.EMPTY_CANDIDATE_LIST);

        AddCandidateDtoRequest addCandidateRequest1 = new AddCandidateDtoRequest("Bob", "Fisher", tokenForCheck1);  //первый избиратель в базе
        AddCandidateDtoRequest addCandidateRequest2 = new AddCandidateDtoRequest("Tim", "Fisher", tokenForCheck2);  //второй избиратель в базе
        AddCandidateDtoRequest addCandidateRequest3 = new AddCandidateDtoRequest("Bill", "Fisher", tokenForCheck1); //не существует такого избирателя в базе
        String jsonAddCandidateRequest1 = new Gson().toJson(addCandidateRequest1);
        String jsonAddCandidateRequest2 = new Gson().toJson(addCandidateRequest2);
        String jsonAddCandidateRequest3 = new Gson().toJson(addCandidateRequest3);
        String jsonAddCandidateResponse1 = server.addCandidate(jsonAddCandidateRequest1);
        String jsonAddCandidateResponse2 = server.addCandidate(jsonAddCandidateRequest2);
        String jsonAddCandidateResponse3 = server.addCandidate(jsonAddCandidateRequest3);
        AddCandidateDtoResponse addCandidateResponse1 = new Gson().fromJson(jsonAddCandidateResponse1, AddCandidateDtoResponse.class);
        AddCandidateDtoResponse addCandidateResponse2 = new Gson().fromJson(jsonAddCandidateResponse2, AddCandidateDtoResponse.class);

        assertEquals(new Gson().toJson(addCandidateResponse1.getToken()), new Gson().toJson(tokenForCheck1));
        assertEquals(new Gson().toJson(addCandidateResponse2.getToken()), new Gson().toJson(tokenForCheck2));
        assertEquals(new Gson().toJson(exception1.getErrorCode().getErrorString()), jsonAddCandidateResponse3);

        assertEquals(m + 2, Database.getInstance().getCandidateMap().size()); // оба добавились но только первый согласен быть кандидатом

        server.stopServer(null);
    }

    @Test
    void agreeToBeCandidate() {
        Server server = new Server();
        server.startServer(null);
        int m = Database.getInstance().getCandidateMap().size(); //0
        UUID tokenForCheck = (UUID) Database.getInstance().getValidTokens().toArray()[1];  //токен Tim Fisher

        AddCandidateDtoRequest addCandidateRequest = new AddCandidateDtoRequest("Bob", "Fisher", tokenForCheck);
        String jsonAddCandidateRequest = new Gson().toJson(addCandidateRequest);
        String jsonAddCandidateResponse = server.addCandidate(jsonAddCandidateRequest);

        AgreeToBeCandidateDtoRequest agreeRequest = new AgreeToBeCandidateDtoRequest(tokenForCheck);
        String jsonAgreeRequest = new Gson().toJson(agreeRequest);
        String jsonAgreeResponse = server.agreeToBeCandidate(jsonAgreeRequest);
        AgreeToBeCandidateDtoResponse agreeResponse = new Gson().fromJson(jsonAgreeResponse, AgreeToBeCandidateDtoResponse.class);

        assertEquals(new Gson().toJson(agreeResponse.getToken()), new Gson().toJson(tokenForCheck));
        assertEquals(m + 1, Database.getInstance().getCandidateMap().size());

        server.stopServer(null);
    }

    @Test
    void getAllCandidates() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();

        server.stopServer(null);
    }

    @Test
    void makeProposal() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();

        server.stopServer(null);
    }

    @Test
    void addRating() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();

        server.stopServer(null);
    }

    @Test
    void removeRating() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();

        server.stopServer(null);
    }

    @Test
    void getAllProposals() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();

        server.stopServer(null);
    }

    @Ignore
    void testStartElections() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersMap().size();
        StartElectionsDtoRequest startRequest1 = new StartElectionsDtoRequest(adminTokenForCheck, "Выборы начались");
        String jsonStartRequest1 = new Gson().toJson(startRequest1);
        String jsonStartResponce1 = server.startElections(jsonStartRequest1);
        StartElectionsDtoResponse startResponce1 = new Gson().fromJson(jsonStartResponce1, StartElectionsDtoResponse.class);

        assertEquals(new Gson().toJson(startResponce1.getToken()), new Gson().toJson(adminTokenForCheck));

        StartElectionsDtoRequest startRequest2 = new StartElectionsDtoRequest(adminTokenForCheck, "Выборы еще не начались");
        String jsonStartRequest2 = new Gson().toJson(startRequest2);
        String jsonStartResponce2 = server.startElections(jsonStartRequest2);
        StartElectionsDtoResponse startResponce2 = new Gson().fromJson(jsonStartResponce2, StartElectionsDtoResponse.class);

        assertEquals(new Gson().toJson(startResponce2.getToken()), new Gson().toJson(adminTokenForCheck));

        server.stopServer(null);
    }
}
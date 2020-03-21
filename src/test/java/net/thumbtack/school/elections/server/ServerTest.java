package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.dto.request.voterRequests.GetAllVotersDtoRequest;
import net.thumbtack.school.elections.dto.request.voterRequests.LoginVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.voterRequests.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.voterRequests.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.voterResponses.GetAllVotersDtoResponse;
import net.thumbtack.school.elections.dto.response.voterResponses.LoginVoterDtoResponse;
import net.thumbtack.school.elections.dto.response.voterResponses.LogoutVoterDtoResponse;
import net.thumbtack.school.elections.dto.response.voterResponses.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.model.Voter;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {


    @Test
    public void testRegisterVoterRightInfo() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersList().size();
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Bob", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Tom", "Fisher", "", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        String jsonRequest1 = new Gson().toJson(request1);
        String jsonRequest2 = new Gson().toJson(request2);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        String jsonResult2 = server.registerVoter(jsonRequest2);
        RegisterVoterDtoResponse result1 = new Gson().fromJson(jsonResult1, RegisterVoterDtoResponse.class);
        RegisterVoterDtoResponse result2 = new Gson().fromJson(jsonResult2, RegisterVoterDtoResponse.class);

        assertEquals(n + 2, Database.getInstance().getVotersList().size()); //проверка что запрос был успешным и количество избирателей увеличилось
        assertEquals(new Gson().toJson(result1), jsonResult1);
        assertEquals(new Gson().toJson(result2), jsonResult2);
        server.stopServer(null);
    }

    @Test
    public void testRegisterVoterWrongInfo() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersList().size();
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
        RegisterVoterDtoResponse result = new Gson().fromJson(jsonResult2, RegisterVoterDtoResponse.class);
        String jsonResult3 = server.registerVoter(jsonRequest3);

        assertEquals(new Gson().toJson(result), jsonResult2);
        assertEquals(new Gson().toJson(exception1.getErrorCode().getErrorString()), jsonResult1);
        assertEquals(new Gson().toJson(exception2.getErrorCode().getErrorString()), jsonResult3);
        assertEquals(n + 1, Database.getInstance().getVotersList().size()); //проверка что сработал только request2
        server.stopServer(null);
    }

    @Test
    void testLogoutVoter() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersList().size();
        RegisterVoterDtoRequest registerRequest = new RegisterVoterDtoRequest("Andrey", "Petrov", "Sergeevich", "Zukova", 13, 45, "petrov12345", "qwertyasdf");
        String jsonRegisterRequest = new Gson().toJson(registerRequest);
        String jsonRegisterResult = server.registerVoter(jsonRegisterRequest);
        RegisterVoterDtoResponse registerResult = new Gson().fromJson(jsonRegisterResult, RegisterVoterDtoResponse.class);
        UUID tokenForCheck = registerResult.getToken();

        LogoutVoterDtoRequest logoutRequest = new LogoutVoterDtoRequest(tokenForCheck);
        String jsonLogoutRequest = new Gson().toJson(logoutRequest);
        String jsonLogoutResult = server.logoutVoter(jsonLogoutRequest);
        LogoutVoterDtoResponse logoutVDResponse = new Gson().fromJson(jsonLogoutResult, LogoutVoterDtoResponse.class);

        assertEquals(n + 1, Database.getInstance().getVotersList().size()); //проверка что тестовый избиратель добавился
        assertEquals(new Gson().toJson(logoutVDResponse), jsonLogoutResult);
        server.stopServer(null);
    }

    @Test
    void testLoginVoter() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersList().size();
        Voter testVoter = Database.getInstance().getVotersList().get(0);

        LoginVoterDtoRequest loginRequest = new LoginVoterDtoRequest(testVoter.getLogin(), testVoter.getPassword());
        String jsonLoginRequest = new Gson().toJson(loginRequest);
        String jsonLoginResult = server.loginVoter(jsonLoginRequest);
        LoginVoterDtoResponse loginVDResponse = new Gson().fromJson(jsonLoginResult, LoginVoterDtoResponse.class);

        assertEquals(n, Database.getInstance().getVotersList().size());
        assertEquals(new Gson().toJson(loginVDResponse), jsonLoginResult);
        server.stopServer(null);
    }

    @Test
    void testGetAllVotersList() {
        Server server = new Server();
        server.startServer(null);
        int n = Database.getInstance().getVotersList().size();
        RegisterVoterDtoRequest registerRequest = new RegisterVoterDtoRequest("Ivan", "Makarov", "Sergeevich", "Zukova", 13, 45, "petrov12345", "qwertyasdf");
        String jsonRegisterRequest = new Gson().toJson(registerRequest);
        String jsonRegisterResult = server.registerVoter(jsonRegisterRequest);
        RegisterVoterDtoResponse registerResult = new Gson().fromJson(jsonRegisterResult, RegisterVoterDtoResponse.class); //добавление избирателя для проверки
        UUID tokenForCheck = registerResult.getToken();
        GetAllVotersDtoRequest getVotersRequest = new GetAllVotersDtoRequest(tokenForCheck);
        String jsonGetVotersRequest = new Gson().toJson(getVotersRequest);
        String jsonGetVotersResult = server.getAllVoters(jsonGetVotersRequest);
        GetAllVotersDtoResponse result = new Gson().fromJson(jsonGetVotersResult, GetAllVotersDtoResponse.class);

        assertEquals(new Gson().toJson(result), jsonGetVotersResult);
        assertEquals(n + 1, Database.getInstance().getVotersList().size()); //проверка что тестовый избиратель добавился
        server.stopServer(null);
    }
}
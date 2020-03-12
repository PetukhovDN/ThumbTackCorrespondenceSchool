package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.dto.request.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.exceptions.ElectionsException;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {
    Server server = new Server();
    Gson gson = new Gson();
    String savedDataFileName = "database_name.txt";

    @Test
    public void testServerStartsWithoutExceptions() {
        assertEquals("Сервер еще не запущен", server.isOnline());
        server.startServer(savedDataFileName);
        assertEquals("Сервер запущен", server.isOnline());
        server.startServer(savedDataFileName);
        assertEquals("Сервер уже запущен", server.isOnline());
        server.stopServer(savedDataFileName);
        assertEquals("Сервер остановлен", server.isOnline());
        server.stopServer(savedDataFileName);
        assertEquals("Сервер уже остановлен", server.isOnline());
    }

    @Test
    public void testRegisterVoterRightInfo() {
        server.startServer(savedDataFileName);
        int n = Database.getInstance().getVotersList().size();
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Bob", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Tom", "Fisher", "", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        String jsonRequest1 = gson.toJson(request1);
        String jsonRequest2 = gson.toJson(request2);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        String jsonResult2 = server.registerVoter(jsonRequest2);
        RegisterVoterDtoResponse result1 = gson.fromJson(jsonResult1, RegisterVoterDtoResponse.class);
        RegisterVoterDtoResponse result2 = gson.fromJson(jsonResult2, RegisterVoterDtoResponse.class);

        assertEquals(n + 2, Database.getInstance().getVotersList().size()); //проверка что запрос был успешным и количество избирателей увеличилось
        assertEquals(gson.toJson(result1), jsonResult1);
        assertEquals(gson.toJson(result2), jsonResult2);
        server.stopServer(savedDataFileName);
    }

    @Test
    public void testRegisterVoterWrongInfo() {
        server.startServer(savedDataFileName);
        int n = Database.getInstance().getVotersList().size();
        ElectionsException exception1 = new ElectionsException(ExceptionErrorCode.EMPTY_VOTER_LASTNAME);
        ElectionsException exception2 = new ElectionsException(ExceptionErrorCode.DUPLICATE_VOTER);
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Bob", "", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //пустое поле фамилии
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Tim", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //валидный запрос
        RegisterVoterDtoRequest request3 = new RegisterVoterDtoRequest("Tim", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //идентичный предыдущему запрос
        String jsonRequest1 = gson.toJson(request1);
        String jsonRequest2 = gson.toJson(request2);
        String jsonRequest3 = gson.toJson(request3);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        String jsonResult2 = server.registerVoter(jsonRequest2); //запрос для дублирования избирателей
        RegisterVoterDtoResponse result = gson.fromJson(jsonResult2, RegisterVoterDtoResponse.class);
        String jsonResult3 = server.registerVoter(jsonRequest3);

        assertEquals(gson.toJson(result), jsonResult2);
        assertEquals(gson.toJson(exception1.getErrorCode().getErrorString()), jsonResult1);
        assertEquals(gson.toJson(exception2.getErrorCode().getErrorString()), jsonResult3);
        assertEquals(n + 1, Database.getInstance().getVotersList().size()); //проверка что сработал только request2
        server.stopServer(savedDataFileName);
    }

    /**
     * Отдельно тест работает, вместе с остальными нет.
     * Не могу понять как сделать чтобы тесты не зависели друг от друга -
     * они запускаются в случайном порядке и сбивают друг друга.
     */
    @Test
    void testLogoutVoter() {
        server.startServer(savedDataFileName);
        int n = Database.getInstance().getVotersList().size();
        RegisterVoterDtoRequest registerRequest = new RegisterVoterDtoRequest("Andrey", "Petrov", "Sergeevich", "Zukova", 13, 45, "petrov12345", "qwertyasdf");
        String jsonRegisterRequest = gson.toJson(registerRequest);
        String jsonRegisterResult = server.registerVoter(jsonRegisterRequest);
        RegisterVoterDtoResponse registerResult = gson.fromJson(jsonRegisterResult, RegisterVoterDtoResponse.class);
        UUID tokenForCheck = registerResult.getToken();
        LogoutVoterDtoRequest logoutRequest = new LogoutVoterDtoRequest(tokenForCheck);
        String jsonLogoutRequest = gson.toJson(logoutRequest);
        String jsonLogoutResult = server.logoutVoter(jsonLogoutRequest);
//        LogoutVoterDtoResponse logoutVDResponse = gson.fromJson(jsonLogoutResult, LogoutVoterDtoResponse.class);

        assertEquals(n + 1, Database.getInstance().getVotersList().size()); //проверка что избиратель добавился
//        assertEquals(gson.toJson(logoutVDResponse), jsonLogoutResult);
        server.stopServer(savedDataFileName);
    }

    @Test
    void testLoginVoter() {
        server.startServer(savedDataFileName);
        int n = Database.getInstance().getVotersList().size();

        server.stopServer(savedDataFileName);
    }

    @Test
    void testGetAllVotersList() {
        server.startServer(savedDataFileName);
        int n = Database.getInstance().getVotersList().size();
        RegisterVoterDtoRequest registerRequest = new RegisterVoterDtoRequest("Ivan", "Makarov", "Sergeevich", "Zukova", 13, 45, "petrov12345", "qwertyasdf");
        String jsonRegisterRequest = gson.toJson(registerRequest);
        String jsonRegisterResult = server.registerVoter(jsonRegisterRequest);
        RegisterVoterDtoResponse registerResult = gson.fromJson(jsonRegisterResult, RegisterVoterDtoResponse.class); //добавление избирателя для проверки
        String tokenForCheck = registerResult.getToken().toString();
//        String jsonRequest = gson.toJson(tokenForCheck);
//        String jsonResult = server.getAllVotersList(jsonRequest);
//        GetAllVotersResponse result = gson.fromJson(jsonResult, GetAllVotersResponse.class);
//
//        assertEquals(gson.toJson(result), jsonResult);
        assertEquals(n + 1, Database.getInstance().getVotersList().size()); //проверка что избиратель добавился
        server.stopServer(savedDataFileName);
    }
}
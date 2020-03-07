package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.dto.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.dto.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.exceptions.ExceptionErrorCode;
import net.thumbtack.school.elections.exceptions.VoterException;
import org.junit.jupiter.api.Test;

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
        int n = Database.getInstance().getVotersSet().size();
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Bob", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Tom", "Fisher", "", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty");
        String jsonRequest1 = gson.toJson(request1);
        String jsonRequest2 = gson.toJson(request2);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        String jsonResult2 = server.registerVoter(jsonRequest2);
        RegisterVoterDtoResponse result1 = gson.fromJson(jsonResult1, RegisterVoterDtoResponse.class);
        RegisterVoterDtoResponse result2 = gson.fromJson(jsonResult2, RegisterVoterDtoResponse.class);

        assertEquals(n + 2, Database.getInstance().getVotersSet().size()); //проверка что запрос был успешным и количество избирателей увеличилось
        assertEquals(gson.toJson(result1), jsonResult1);
        assertEquals(gson.toJson(result2), jsonResult2);
        server.stopServer(savedDataFileName);
    }

    @Test
    public void testRegisterVoterWrongInfo() {
        server.startServer(savedDataFileName);
        int n = Database.getInstance().getVotersSet().size();
        VoterException exception1 = new VoterException(ExceptionErrorCode.EMPTY_VOTER_LASTNAME);
        VoterException exception2 = new VoterException(ExceptionErrorCode.DUPLICATE_VOTER);
        RegisterVoterDtoRequest request1 = new RegisterVoterDtoRequest("Bob", "", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //пустое поле фамилии
        RegisterVoterDtoRequest request2 = new RegisterVoterDtoRequest("Tim", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //валидный запрос
        RegisterVoterDtoRequest request3 = new RegisterVoterDtoRequest("Tim", "Fisher", "Ivanovich", "Lenina", 40, 277, "bobcherchil122", "1234567qwerty"); //идентичный предыдущему запрос
        String jsonRequest1 = gson.toJson(request1);
        String jsonRequest2 = gson.toJson(request2);
        String jsonRequest3 = gson.toJson(request3);
        String jsonResult1 = server.registerVoter(jsonRequest1);
        String jsonResult2 = server.registerVoter(jsonRequest2); //запрос для дублирования избирателей
        String jsonResult3 = server.registerVoter(jsonRequest3);

        assertEquals(gson.toJson(exception1.getErrorCode().getErrorString()), jsonResult1);
        assertEquals(gson.toJson(exception2.getErrorCode().getErrorString()), jsonResult3);
        assertEquals(n + 1, Database.getInstance().getVotersSet().size()); //проверка что сработал только request2
        server.stopServer(savedDataFileName);
    }

    @Test
    void testLoginVoter() {
        server.startServer(savedDataFileName);
        int n = Database.getInstance().getVotersSet().size();

        server.stopServer(savedDataFileName);
    }

    @Test
    void testLogoutVoter() {
        server.startServer(savedDataFileName);
        int n = Database.getInstance().getVotersSet().size();

        server.stopServer(savedDataFileName);
    }
}
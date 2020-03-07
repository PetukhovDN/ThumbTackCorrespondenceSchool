package net.thumbtack.school.elections.server;

import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.service.LoginVoterService;
import net.thumbtack.school.elections.service.LogoutVoterService;
import net.thumbtack.school.elections.service.RegisterVoterService;

import java.io.*;

public class Server implements Serializable {
    private static final long serialVersionUID = -6335324644020763893L;
    RegisterVoterService registerVoterService;
    LoginVoterService loginVoterService;
    LogoutVoterService logoutVoterService;
    Database database = null;
    private String isOnline = "Сервер еще не запущен";

    public String isOnline() {
        return isOnline;
    }

    public void startServer(String savedDataFileName) {
        if (!isOnline.equals("Сервер запущен")) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(savedDataFileName))) {
                if (!(objectInputStream.readObject() == null)) {
                    while (objectInputStream.available() > 0) {
                        database = (Database) objectInputStream.readObject();
                    }
                }
                isOnline = "Сервер запущен";
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        } else {
            isOnline = "Сервер уже запущен";
        }
    }

    public void stopServer(String saveDataFileName) {
        if (!isOnline.equals("Сервер остановлен")) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(saveDataFileName))) {
                database = Database.getInstance();
                objectOutputStream.writeObject(database);
                isOnline = "Сервер остановлен";
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            isOnline = "Сервер уже остановлен";
        }
    }

    public String registerVoter(String requestJsonString) {
        registerVoterService = new RegisterVoterService(requestJsonString);
        return registerVoterService.validateAndCreate();
    }

    public String loginVoter(String requestJsonString) {
        loginVoterService = new LoginVoterService(requestJsonString);
        return loginVoterService.validateAndLogin();
    }

    public String logoutVoter(String requestJsonString) {
        logoutVoterService = new LogoutVoterService(requestJsonString);
        return logoutVoterService.validateAndLogout();
    }

    public String addCandidate(String requestJsonString) {
        return "";
    }


}

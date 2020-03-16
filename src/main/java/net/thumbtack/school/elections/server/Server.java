package net.thumbtack.school.elections.server;

import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.service.CandidateService;
import net.thumbtack.school.elections.service.ProposalService;
import net.thumbtack.school.elections.service.VoterService;

import java.io.*;


/**
 * Общая информация (как я понял):
 * Класс сервер запускает и останавливает сервер (инициализирует базу), выполняет все запросы - для каждого запроса свой метод.
 * Каждый метод делегирует запрос соответствующему классу сервиса.
 * Класс сервиса отправляет запрос на проверку в соответствующий класс DTO запроса,
 * где происходит грамматическая проверка запроса - на null, пустой запрос.
 * Если проверка прошла успешно, на основе созданного обьекта DTO создается уже конкретный объект модели.
 * Затем создается объект DAO, которому передается объект модели.
 * В DAO в отдельном методе (отдельном для каждого вида запроса) происходит лошическая проверка запроса -
 * существует ли уже такой объект в базе, валиден ли он с точки зрения условия базы данных.
 * Если всё в порядке то этот метод возвращает определенное значение, на основе которого в классе сервиса формируется объект DTO ответа.
 * Сервис преобразует этот объект в Json и возвращает в метод запроса класса Сервер.
 * Все классы пробрасывают исключение ElectionsException до класса сервиса, где это исключение ловится.
 * Если исключение было поймано, то оно преобразуется в Json и класс сервиса возвращает его в метод запроса класса Сервер.
 * Заключение:
 * Логика обработки запросов вся лежит на классах сервиса, работа с БД - на классах Data Access Object,
 * классы типа model - объекты для заполнения БД,
 * классы типа DTO Response = классы для создания ответов (преобразование результата метода DAO в объект DTO Response),
 */

public class Server implements Serializable {
    private static final long serialVersionUID = -6335324644020763893L; //сервер сериализуется? или только БД
    VoterService voterService;
    CandidateService candidateService;
    ProposalService proposalService;
    Database database = null;
    private String isOnline = "Сервер еще не запущен";

    public String isOnline() {
        return isOnline;
    }

    public void startServer(String savedDataFileName) {
        if (!isOnline.equals("Сервер запущен") && !isOnline.equals("Сервер уже запущен")) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(savedDataFileName))) {
                if (!(objectInputStream.readObject() == null)) {
                    while (objectInputStream.available() > 0) {
                        database = (Database) objectInputStream.readObject();
                    }
                } else {
                    database = new Database();
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
        if (!isOnline.equals("Сервер остановлен") && !isOnline.equals("Сервер уже остановлен")) {
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
        voterService = new VoterService(requestJsonString);
        return voterService.registerVoter();
    }

    public String loginVoter(String requestJsonString) {
        voterService = new VoterService(requestJsonString);
        return voterService.loginVoter();
    }

    public String logoutVoter(String requestJsonString) {
        voterService = new VoterService(requestJsonString);
        return voterService.logoutVoter();
    }

    public String getAllVoters(String requestJsonString) {
        voterService = new VoterService(requestJsonString);
        return voterService.getAllVoters();
    }

    public String makeProposal(String requestJsonString) {
        proposalService = new ProposalService(requestJsonString);
        return proposalService.makeProposal();
    }

    public String addCandidate(String requestJsonString) {
        candidateService = new CandidateService(requestJsonString);
        return candidateService.addCandidate();
    }


}

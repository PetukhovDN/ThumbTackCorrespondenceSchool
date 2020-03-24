package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
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
    private static Database database;
    private Boolean electionsStarted = false;

    private static VoterService voterService;
    private static CandidateService candidateService;
    private static ProposalService proposalService;

    public Boolean getElectionsStarted() {
        return electionsStarted;
    }

    public void setElectionsStarted(Boolean electionsStarted) {
        this.electionsStarted = electionsStarted;
    }

    public void startServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(savedDataFileName))) {
                if (objectInputStream.readObject() != null) {
                    while (objectInputStream.available() > 0) {
                        database = (Database) objectInputStream.readObject();
                    }
                }
                else {
                    database = new Database();
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
            database = new Database();
        }
    }

    public void stopServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(savedDataFileName))) {
                database = Database.getInstance();
                objectOutputStream.writeObject(database);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            database = null;
        }
    }

    public String registerVoter(String requestJsonString) {
        if (electionsStarted) {
            return new Gson().toJson("Выборы уже начались");
        }
        voterService = new VoterService(requestJsonString);
        return voterService.registerVoter();
    }

    public String loginVoter(String requestJsonString) {
        if (electionsStarted) {
            return new Gson().toJson("Выборы уже начались");
        }
        voterService = new VoterService(requestJsonString);
        return voterService.loginVoter();
    }

    public String logoutVoter(String requestJsonString) {
        if (electionsStarted) {
            return new Gson().toJson("Выборы уже начались");
        }
        voterService = new VoterService(requestJsonString);
        return voterService.logoutVoter();
    }

    public String getAllVoters(String requestJsonString) {
        voterService = new VoterService(requestJsonString);
        return voterService.getAllVoters();
    }

    public String addCandidate(String requestJsonString) {
        if (electionsStarted) {
            return new Gson().toJson("Выборы уже начались");
        }
        candidateService = new CandidateService(requestJsonString);
        return candidateService.addCandidate();
    }

    public String agreeToBeCandidate(String requestJsonString) {
        if (electionsStarted) {
            return new Gson().toJson("Выборы уже начались");
        }
        candidateService = new CandidateService(requestJsonString);
        return candidateService.agreeToBeCandidate();
    }

    public String getAllCandidates(String requestJsonString) {
        candidateService = new CandidateService(requestJsonString);
        return candidateService.getAllAgreedCandidates();
    }

    public String makeProposal(String requestJsonString) {
        if (electionsStarted) {
            return new Gson().toJson("Выборы уже начались");
        }
        proposalService = new ProposalService(requestJsonString);
        return proposalService.makeProposal();
    }

    public String addRating(String requestJsonString) {
        if (electionsStarted) {
            return new Gson().toJson("Выборы уже начались");
        }
        proposalService = new ProposalService(requestJsonString);
        return proposalService.addRatingForProposal();
    }

    public String removeRating(String requestJsonString) {
        if (electionsStarted) {
            return new Gson().toJson("Выборы уже начались");
        }
        proposalService = new ProposalService(requestJsonString);
        return proposalService.removeRatingFromProposal();
    }

    public String getAllProposals(String requestJsonString) {
        proposalService = new ProposalService(requestJsonString);
        return proposalService.getAllProposals();
    }


}

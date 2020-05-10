package net.thumbtack.school.elections.server;

import com.google.gson.Gson;
import net.thumbtack.school.elections.database.Database;
import net.thumbtack.school.elections.service.CandidateService;
import net.thumbtack.school.elections.service.ProposalService;
import net.thumbtack.school.elections.service.StartElectionsService;
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
 * В DAO в отдельном методе (отдельном для каждого вида запроса) происходит логическая проверка запроса -
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

/*
 * TODO:
 *  Дописать документацию.
 *  Добавить кандидата "против всех".
 *  Тесты, тесты...
 */

public class Server implements Serializable {
    private static final long serialVersionUID = -6335324644020763893L; //сервер сериализуется? или только БД
    public static Gson gson = new Gson();
    private static Database database;
    private static VoterService voterService = new VoterService();
    private static CandidateService candidateService = new CandidateService();
    private static ProposalService proposalService = new ProposalService();
    private static StartElectionsService startElectionsService = new StartElectionsService();

    public void startServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(savedDataFileName))) {
                if (objectInputStream.readObject() != null) {
                    while (objectInputStream.available() > 0) {
                        database = (Database) objectInputStream.readObject();
                    }
                } else {
                    database = new Database();
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        } else {
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
        } else {
            database = null;
        }
    }

    public String registerVoter(String requestJsonString) {
        return voterService.registerVoter(requestJsonString);
    }

    public String loginVoter(String requestJsonString) {
        return voterService.loginVoter(requestJsonString);
    }

    public String logoutVoter(String requestJsonString) {
        return voterService.logoutVoter(requestJsonString);
    }

    public String getAllVoters(String requestJsonString) {
        return voterService.getAllVoters(requestJsonString);
    }

    public String addCandidate(String requestJsonString) {
        return candidateService.addCandidate(requestJsonString);
    }

    public String agreeToBeCandidate(String requestJsonString) {
        return candidateService.agreeToBeCandidate(requestJsonString);
    }

    public String addProposalToCandidateProgram(String requestJsonString) {
        return candidateService.addProposalToCandidateProgram(requestJsonString);
    }

    public String removeProposalFromCandidateProgram(String requestJsonString) {
        return candidateService.removeProposalFromCandidateProgram(requestJsonString);
    }

    public String getAllCandidates(String requestJsonString) {
        return candidateService.getAllAgreedCandidates(requestJsonString);
    }

    public String makeProposal(String requestJsonString) {
        return proposalService.makeProposal(requestJsonString);
    }

    public String addRating(String requestJsonString) {
        return proposalService.addRatingForProposal(requestJsonString);
    }

    public String removeRating(String requestJsonString) {
        return proposalService.removeRatingFromProposal(requestJsonString);
    }

    public String getAllProposalsWithRate(String requestJsonString) {
        return proposalService.getAllProposalsWithRate(requestJsonString);
    }

    public String getAllProposalsFromVoter(String requestJsonString) {
        return proposalService.getAllProposalsFromVoter(requestJsonString);
    }

    public String startElections(String requestJsonString) {
        return startElectionsService.startElections(requestJsonString);
    }

    public String voteForCandidate(String requestJsonString) {
        return startElectionsService.voteForCandidate(requestJsonString);
    }

    public String chooseMajorAndStopElections(String requestJsonString) {
        return startElectionsService.chooseMajor(requestJsonString);
    }

}

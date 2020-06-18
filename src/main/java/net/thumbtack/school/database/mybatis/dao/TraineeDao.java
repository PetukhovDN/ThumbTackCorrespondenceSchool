package net.thumbtack.school.database.mybatis.dao;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Trainee;

import java.util.List;

public interface TraineeDao {

    // вставляет Trainee в базу данных. Если group не null,  Trainee после вставки принадлежит этой группе
    // в противном случае Trainee не принадлежит никакой группе
    Trainee insert(Group group, Trainee trainee);

    // получает Trainee по его ID. Если такого ID нет, метод возвращает null
    Trainee getById(int id);

    // получает список всех Trainee, независимо от их Group. Если БД не содержит ни одного Trainee, метод возвращает пустой список
    List<Trainee> getAll();

    // изменяет Trainee в базе данных. Метод не изменяет принадлежности Trainee к Group
    Trainee update(Trainee trainee);

    // получает список всех Trainee при условиях
    // если firstName не равен null - только имеющих это имя
    // если lastName не равен null - только имеющих эту фамилию
    // если rating не равен null - только имеющих эту оценку
    List<Trainee> getAllWithParams(String firstName, String lastName, Integer rating);

    // вставляет список из Trainee в базу данных. Вставленные Trainee не принадлежат никакой группе
    void batchInsert(List<Trainee> trainees);

    // удаляет Trainee из базы данных
    void delete(Trainee trainee);

    // удаляет все Trainee из базы данных
    void deleteAll();


}

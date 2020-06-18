package net.thumbtack.school.database.mybatis.dao;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.util.List;

public interface GroupDao {

    // вставляет Group в базу данных, привязывая ее к School.
    Group insert(School school, Group group);

    // изменяет Group  в базе данных, принадлежность к School не меняется
    Group update(Group group);

    // получает список всех Group, независимо от School
    List<Group> getAll();

    // удаляет Group  в базе данных, при этом все Trainee оказываеются не принадлежащими никакой Group
    void delete(Group group);

    // переводит Trainee в Group. Если Trainee не принадлежал никакой Group, добавляет его в Group
    Trainee moveTraineeToGroup(Group group, Trainee trainee);

    // удаляет Trainee из Group, после этого Trainee не принадлежит никакой Group
    void deleteTraineeFromGroup(Trainee trainee);

    // добавляет Subject к Group
    void addSubjectToGroup(Group group, Subject subject);
}
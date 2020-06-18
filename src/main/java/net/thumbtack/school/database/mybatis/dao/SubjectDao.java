package net.thumbtack.school.database.mybatis.dao;

import net.thumbtack.school.database.model.Subject;

import java.util.List;

public interface SubjectDao {

    // вставляет Subject в базу данных.
    Subject insert(Subject subject);

    // получает Subject по его ID. Если такого ID нет, метод возвращает null
    Subject getById(int id);

    // получает список всех Subject. Если БД не содержит ни одного Subject, метод возвращает пустой список
    List<Subject> getAll();

    // изменяет Subject  в базе данных
    Subject update(Subject subject);

    // удаляет Subject из базы данных
    void delete(Subject subject);

    // удаляет все Subject из базы данных
    void deleteAll();


}
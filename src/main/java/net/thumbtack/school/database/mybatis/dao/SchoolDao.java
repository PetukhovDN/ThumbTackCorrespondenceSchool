package net.thumbtack.school.database.mybatis.dao;

import java.util.List;

import net.thumbtack.school.database.model.School;

public interface SchoolDao {

    // вставляет School в базу данных.
    School insert(School school);

    // получает School по его ID. Если такого ID нет, метод должен возвращать null
    School getById(int id);

    // получает список всех School вместе с Group, Subject, и Trainee, используя LAZY подход. Если БД не содержит ни одной School, метод должен возвращать пустой список
    List<School> getAllLazy();

    // получает список всех School вместе с Group, Subject, и Trainee, используя JOIN подход. Если БД не содержит ни одной School, метод должен возвращать пустой список
    List<School> getAllUsingJoin();

    // изменяет School  в базе данных
    void update(School school);

    // удаляет School  в базе данных, при этом удаляются все Group
    void delete(School school);

    // удаляет все School  в базе данных, при этом удаляются все Group для каждой School
    void deleteAll();

    // трансакционно вставляет School вместе с ее Group, со всеми Trainee этих Group, и привязывает все Subject для этих Group
    // предполагается, что сами Subject были вставлены раньше
    School insertSchoolTransactional(School school2018);
}
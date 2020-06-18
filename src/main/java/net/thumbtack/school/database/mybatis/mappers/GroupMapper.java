package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface GroupMapper {
    @Insert("INSERT INTO groups ( name, room, school_id) VALUES ( #{group.name}, #{group.room}, #{school.id} )")
    @Options(useGeneratedKeys = true, keyProperty = "group.id")
    Integer insert(@Param("group") Group group, @Param("school") School school);

    @Update("UPDATE groups SET name = #{group.name}, room = #{group.room} WHERE id = #{group.id} ")
    void update(@Param("group") Group group);

    @Select("SELECT * FROM groups WHERE school_id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroup", fetchType = FetchType.LAZY)),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroup", fetchType = FetchType.LAZY))})
    List<Group> getAll();

    @Delete("DELETE FROM groups WHERE id = #{group.id}")
    void delete(@Param("group") Group group);

    @Update("UPDATE trainee SET group_id = #{group.id} WHERE id = #{trainee.id} ")
    void moveTraineeToGroup(@Param("group") Group group, @Param("trainee") Trainee trainee);

    @Update("UPDATE trainee SET group_id = NULL WHERE id = #{trainee.id} ")
    void deleteTraineeFromGroup(@Param("trainee") Trainee trainee);

    @Insert("INSERT INTO subject_group (group_id, subject_id) VALUES ( #{group.id}, #{subject.id} )")
    Integer addSubjectToGroup(@Param("group") Group group, @Param("subject") Subject subject);
}

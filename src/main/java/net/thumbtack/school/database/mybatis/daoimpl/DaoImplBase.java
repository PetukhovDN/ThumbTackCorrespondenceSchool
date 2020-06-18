package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.mybatis.mappers.GroupMapper;
import net.thumbtack.school.database.mybatis.mappers.SchoolMapper;
import net.thumbtack.school.database.mybatis.mappers.SubjectMapper;
import net.thumbtack.school.database.mybatis.mappers.TraineeMapper;
import net.thumbtack.school.database.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class DaoImplBase {

    protected SqlSession getSession() {
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected SchoolMapper getSchoolMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SchoolMapper.class);
    }

    protected GroupMapper getGroupMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(GroupMapper.class);
    }

    protected TraineeMapper getTraineeMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(TraineeMapper.class);
    }

    protected SubjectMapper getSubjectMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SubjectMapper.class);
    }

}
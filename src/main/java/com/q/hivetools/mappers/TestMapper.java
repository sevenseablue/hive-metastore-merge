package com.q.hivetools.mappers;

import com.q.hivetools.service.MyBatisUtil;
import com.q.hivetools.meta.Dbs;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class TestMapper {
    private static final Logger logger = Logger.getLogger(TestMapper.class.getName());
    private String sourceName;

    public TestMapper(String sourceName) {
        this.sourceName = sourceName;
    }

    public List<Dbs> getDbs() {
        logger.info("getDbs >>>>>> ");
        List<Dbs> list = null;
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory(this.sourceName).openSession();
        try {
            String statement = "com.q.hivetools.mappers.TestMapper.getDbs";
            list = sqlSession.selectList(statement);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }


        for (Dbs dbs : list) {
            logger.info(dbs.toString());
        }
        logger.info("getDbs <<<<<<<< ");
        return list;
    }
}

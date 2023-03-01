package com.smq.service;

import com.smq.mapper.BrandMapper;
import com.smq.mapper.UserMapper;
import com.smq.pojo.Brand;
import com.smq.pojo.User;
import com.smq.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    public User login(String username, String password){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(username, password);
        sqlSession.close();
        return user;
    }
    public boolean register(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.selectByUsername(user.getUsername());
        if(user1 == null){
            mapper.add(user);
            System.out.println(user);
            sqlSession.commit();

        }
        sqlSession.close();
        return user1 == null;
    }
}

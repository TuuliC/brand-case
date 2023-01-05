package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author tuuli
 * @time Created in 2022/11/12 20:27
 * @description
 */
public class UserServiceImpl implements UserService {

    //1.创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public User login(String username, String password) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4.调用方法
        User user = mapper.select(username, password);

        sqlSession.close();

        return user;
    }

    @Override
    public boolean register(User user) {
        //2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);


        User u = mapper.selectByUsername(user.getUsername());

        if (u == null) {
            //用户不存在，进行注册
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return u == null;
    }
}

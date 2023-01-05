package com.itheima.service;

import com.itheima.pojo.User;

/**
 * @author tuuli
 * @time Created in 2022/11/12 20:25
 * @description
 */
public interface UserService {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean register(User user);
}

package com.soldier.dao;

import com.soldier.entitys.User;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/6 21:37
 * @Desc: 用户DAO
 */
public interface UserDao {

    //用户注册
    public void register(User user) throws Exception;

    //用户登录
    public User login(String username, String password) throws Exception;

    //修改用户信息
    public void update(User user) throws Exception;
}

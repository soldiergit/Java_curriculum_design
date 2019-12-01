package com.soldier.dao.impl;

import com.soldier.dao.UserDao;
import com.soldier.db.JdbcTemplete;
import com.soldier.entitys.User;
import com.soldier.handler.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/7 8:57
 * @Desc:
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplete jdbcTemplete;
    public UserDaoImpl() {
        jdbcTemplete = new JdbcTemplete();
    }

    //用户注册
    @Override
    public void register(User user) throws Exception {
        String sql = "insert into user(UserName,PassWord,userType,CreatedDate,LastmodifyDate)values(?,?,?,?,?)";
        user.setCreatedDate(new Date());  //注册时间为当前
        user.setLastmodifyDate(new Date());
        jdbcTemplete.updete(sql, user.getUserName(),user.getPassWord(),user.getUserType(),user.getCreatedDate(),user.getLastmodifyDate());
    }

    //用户登录
    @Override
    public User login(String username, String password) throws Exception {
        String sql = "select * from user where username=? and password=? ";
        return (User)jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                User user = null;
                if (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt(1));
                    user.setUserName(username);
                    user.setPassWord(password);
                    user.setUserType(rs.getInt(4));
                    user.setCreatedDate(rs.getDate(5));
                    user.setLastmodifyDate(rs.getDate(6));
                }
                return user;
            }
        }, username, password);
    }

    //修改密码等...
    @Override
    public void update(User user) throws SQLException {
        String sql = "update user set UserName=?,PassWord=?,userType=?,CreatedDate=?,LastmodifyDate=? where UserId=?";
        jdbcTemplete.updete(sql, user.getUserName(),user.getPassWord(),user.getUserType(),user.getCreatedDate(),
                new java.sql.Date(user.getLastmodifyDate().getTime()),user.getUserId());
    }


}

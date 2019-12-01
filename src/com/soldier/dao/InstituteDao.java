package com.soldier.dao;

import com.soldier.entitys.Institute;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/6 21:35
 * @Desc: 学院类DAO
 */
public interface InstituteDao {

    //增加方法
    public void add(Institute institute) throws SQLException;

    //更新方法
    public void update(Institute institute) throws SQLException;

    //删除方法
    public void delete(int id) throws SQLException;

    //查找方法
    public Institute findById(int id) throws SQLException;

    //查找所有
    public List<Institute> findAll() throws SQLException;
}

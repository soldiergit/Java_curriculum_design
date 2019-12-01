package com.soldier.dao;

import com.soldier.entitys.Title;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/6 21:36
 * @Desc: 教师职务DAO
 */
public interface TitleDao {

    //增加方法
    public void add(Title title) throws SQLException;

    //更新方法
    public void update(Title title) throws SQLException;

    //删除方法
    public void delete(int id) throws SQLException;

    //查找方法
    public Title findById(int id) throws SQLException;

    //查找所有
    public List<Title> findAll() throws SQLException;
}

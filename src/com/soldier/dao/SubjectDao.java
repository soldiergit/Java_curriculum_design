package com.soldier.dao;

import com.soldier.entitys.Subject;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/6 21:34
 * @Desc: 选题类DAO
 */
public interface SubjectDao {

    //增加方法
    public void add(Subject subject) throws SQLException;

    //更新录取人数
    public void updateAlreadyFillNum(int subjectId, String teacherId, int alreadyFillNum);

    //更新方法
    public void update(Subject subject) throws SQLException;

    //删除方法
    public void delete(int id) throws SQLException;

    //查找方法
    public Subject findById(int id) throws SQLException;

    //查找所有
    public List<Subject> findAll() throws SQLException;
}

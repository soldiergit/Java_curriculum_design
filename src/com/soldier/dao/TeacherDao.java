package com.soldier.dao;

import com.soldier.entitys.Teacher;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/6 21:35
 * @Desc: 教师DAO
 */
public interface TeacherDao {

    //查询个人已发布选题数
    public void addMySubjectNum(int mySubjectNum, String teacherId) throws SQLException;

    //增加方法
    public void add(Teacher teacher) throws SQLException;

    //更新方法
    public void update(Teacher teacher) throws SQLException;

    //删除方法
    public void delete(String teacherId) throws SQLException;

    //查找方法
    public Teacher findById(String teacherId) throws SQLException;

    //查找所有
    public List<Teacher> findAll() throws SQLException;
}

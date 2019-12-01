package com.soldier.dao.impl;

import com.soldier.dao.TeacherDao;
import com.soldier.db.JdbcTemplete;
import com.soldier.entitys.Subject;
import com.soldier.entitys.Teacher;
import com.soldier.handler.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/7 8:57
 * @Desc:
 */
public class TeacherDaoImpl implements TeacherDao {

    private JdbcTemplete jdbcTemplete;
    public TeacherDaoImpl() {
        jdbcTemplete = new JdbcTemplete();
    }

    @Override
    public void addMySubjectNum(int mySubjectNum, String teacherId) throws SQLException {
        mySubjectNum += 1;
        String sql = "update teacher set mySubjectNum=? where teacherId=?";
        jdbcTemplete.updete(sql,mySubjectNum,teacherId);
    }

    @Override
    public void add(Teacher teacher) throws SQLException {
        String sql = "insert into teacher(teacherId,titleId,instituteId,teacherName,sex,tel,email)values(?,?,?,?,?,?,?)";
        jdbcTemplete.updete(sql, teacher.getTeacherId(),teacher.getTitleId(),teacher.getInstituteId(),teacher.getTeacherName(),teacher.getSex(),teacher.getTel(),teacher.getEmail());
    }

    @Override
    public void update(Teacher teacher) throws SQLException {
        String sql = "update teacher set titleId=?,instituteId=?,teacherName=?,sex=?,tel=?,email=? where teacherId=?";
        jdbcTemplete.updete(sql, teacher.getTitleId(),teacher.getInstituteId(),teacher.getTeacherName(),teacher.getSex(),
                teacher.getTel(),teacher.getEmail(),teacher.getTeacherId());
    }

    @Override
    public void delete(String teacherId) throws SQLException {
        String sql = "delete from teacher where teacherId=?";
        jdbcTemplete.updete(sql,teacherId);
    }

    @Override
    public Teacher findById(String teacherId) throws SQLException {
        String sql = "select * from teacher where teacherId=?";
        return (Teacher) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                Teacher teacher = null;
                if (rs.next()) {
                    teacher = new Teacher();
                    teacher.setTeacherId(teacherId);
                    teacher.setTitleId(rs.getInt(2));
                    teacher.setInstituteId(rs.getInt(3));
                    teacher.setTeacherName(rs.getString(4));
                    teacher.setSex(rs.getString(5));
                    teacher.setTel(rs.getString(6));
                    teacher.setEmail(rs.getString(7));
                    teacher.setMySubjectNum(rs.getInt(8));
                }
                return teacher;
            }
        }, teacherId);
    }

    @Override
    public List<Teacher> findAll() throws SQLException {
        String sql = "select * from teacher";
        return (List<Teacher>) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                List<Teacher> teacherList = new ArrayList<Teacher>();
                Teacher teacher = null;
                while (rs.next()) {
                    teacher = new Teacher();
                    teacher.setTeacherId(rs.getString(1));
                    teacher.setTitleId(rs.getInt(2));
                    teacher.setInstituteId(rs.getInt(3));
                    teacher.setTeacherName(rs.getString(4));
                    teacher.setSex(rs.getString(5));
                    teacher.setTel(rs.getString(6));
                    teacher.setEmail(rs.getString(7));
                    teacher.setMySubjectNum(rs.getInt(8));
                    teacherList.add(teacher);
                }
                return teacherList;
            }
        });
    }
}

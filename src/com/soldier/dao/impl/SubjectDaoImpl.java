package com.soldier.dao.impl;

import com.soldier.dao.SubjectDao;
import com.soldier.db.JdbcTemplete;
import com.soldier.entitys.Subject;
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
public class SubjectDaoImpl implements SubjectDao {

    private JdbcTemplete jdbcTemplete;
    public SubjectDaoImpl() {
        jdbcTemplete = new JdbcTemplete();
    }

    /**
     * 老师添加选题时，默认alreadyFillNum（已被学生填报的数量）为0，不用填
     * @param subject
     * @throws SQLException
     */
    @Override
    public void add(Subject subject) throws SQLException {
        String sql = "insert into subject(teacherId,subjectName,subjectNum,subjectAsk)values(?,?,?,?)";
        jdbcTemplete.updete(sql, subject.getTeacherId(),subject.getSubjectName(),subject.getSubjectNum(),subject.getSubjectAsk());
    }

    /**
     * 老师录取学生
     * @param subjectId
     * @param teacherId
     */
    @Override
    public void updateAlreadyFillNum(int subjectId, String teacherId, int alreadyFillNum) {
        alreadyFillNum +=1;
        String sql = "update subject set alreadyFillNum=? where subjectId=? and teacherId=?";
        jdbcTemplete.updete(sql,alreadyFillNum,subjectId,teacherId);
    }

    /**
     * 老师修改选题时，不会解散学生群体，且老师ID不会改变，数量等于大于原来数量
     * @param subject
     * @throws SQLException
     */
    @Override
    public void update(Subject subject) throws SQLException {
        String sql = "update subject set subjectName=?,subjectNum=?,subjectAsk=? where subjectId=?";
        jdbcTemplete.updete(sql, subject.getSubjectName(),subject.getSubjectNum(),subject.getSubjectAsk(),subject.getSubjectId());
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from subject where subjectId=?";
        jdbcTemplete.updete(sql,id);
    }

    @Override
    public Subject findById(int id) throws SQLException {
        String sql = "select * from subject where subjectId=?";
        return (Subject) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                Subject subject = null;
                if (rs.next()) {
                    subject = new Subject();
                    subject.setSubjectId(id);
                    subject.setTeacherId(rs.getString(2));
                    subject.setSubjectName(rs.getString(3));
                    subject.setSubjectNum(rs.getInt(4));
                    subject.setAlreadyFillNum(rs.getInt(5));
                    subject.setSubjectAsk(rs.getString(6));
                }
                return subject;
            }
        }, id);
    }

    @Override
    public List<Subject> findAll() throws SQLException {
        String sql = "select * from subject";
        return (List<Subject>) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                List<Subject> subjectList = new ArrayList<Subject>();
                Subject subject = null;
                while (rs.next()) {
                    subject = new Subject();
                    subject.setSubjectId(rs.getInt(1));
                    subject.setTeacherId(rs.getString(2));
                    subject.setSubjectName(rs.getString(3));
                    subject.setSubjectNum(rs.getInt(4));
                    subject.setAlreadyFillNum(rs.getInt(5));
                    subject.setSubjectAsk(rs.getString(6));
                    subjectList.add(subject);
                }
                return subjectList;
            }
        });
    }
}

package com.soldier.dao.impl;

import com.soldier.dao.StudentDao;
import com.soldier.db.JdbcTemplete;
import com.soldier.entitys.Student;
import com.soldier.handler.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/7 8:35
 * @Desc: 学生DAO实现
 */
public class StudentDaoImpl implements StudentDao {

    private JdbcTemplete jdbcTemplete;
    public StudentDaoImpl() {
        jdbcTemplete = new JdbcTemplete();
    }

    /**
     * 用于管理员添加学生，志愿填报由学生自己添加
     * @param student
     * @throws SQLException
     */
    @Override
    public void add(Student student) throws SQLException {
        String sql = "insert into student(studentNo,instituteId,studentName,sex,politicsType," +
                "email,tel,score) values(?,?,?,?,?,?,?,?)";
        jdbcTemplete.updete(sql,student.getStudentNo(),student.getInstituteId(),student.getStudentName(),student.getSex(),student.getPoliticsType(),
                student.getEmail(),student.getTel(),student.getScore());
    }

    /**
     * 志愿被老师录取之后，1-第一志愿被录取；2-第二志愿被录取
     * @param admissionSituation
     * @param studentNo
     * @throws SQLException
     */
    @Override
    public void updateAdmissionSituation(int admissionSituation, String studentNo) throws SQLException {
        String sql = "update student set admissionSituation=? where studentNo=?";
        jdbcTemplete.updete(sql,admissionSituation,studentNo);
    }

    /**
     * 学生自己修改自己的个人信息时，不会把志愿也一起修改，志愿修改在另一个页面
     * @param student
     * @throws SQLException
     */
    @Override
    public void update(Student student) throws SQLException {
        String sql = "update student set instituteId=?,studentName=?,sex=?,politicsType=?,email=?,tel=?" +
                " where studentNo=?";
        jdbcTemplete.updete(sql,student.getInstituteId(),student.getStudentName(),student.getSex(),student.getPoliticsType(),
                student.getEmail(),student.getTel(),student.getStudentNo());
    }

    /**
     * 学生自己填报第一志愿
     * @param subjectId
     * @throws SQLException
     */
    @Override
    public void addFirstChoose(int subjectId,String studentNo) throws SQLException {
        String sql = "update student set firstChooseId=? where studentNo=?";
        jdbcTemplete.updete(sql,subjectId,studentNo);
    }

    /**
     * 学生自己填报第二志愿
     * @param subjectId
     * @throws SQLException
     */
    @Override
    public void addSecondChoose(int subjectId,String studentNo) throws SQLException {
        String sql = "update student set secondChooseId=? where studentNo=?";
        jdbcTemplete.updete(sql,subjectId,studentNo);
    }

    /**
     * 学生查看录取情况
     * @param studentNo
     * @throws SQLException
     */
    @Override
    public int admissionSituation(String studentNo) throws SQLException {
        String sql = "select admissionSituation from student where studentNo=?";
        return (int) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                int admissionSituation = 0;
                if (rs.next()) {
                    admissionSituation = rs.getInt(1);
                }
                return admissionSituation;
            }
        }, studentNo);
    }

    @Override
    public void delete(String studentNo) throws SQLException {
        String sql = "delete from student where studentNo=?";
        jdbcTemplete.updete(sql,studentNo);
    }

    @Override
    public Student findById(String studentNo) throws SQLException {
        String sql = "select * from student where studentNo=?";
        return (Student) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                Student student = null;
                if (rs.next()) {
                    student = new Student();
                    student.setStudentNo(studentNo);
                    student.setInstituteId(rs.getInt(2));
                    student.setStudentName(rs.getString(3));
                    student.setSex(rs.getString(4));
                    student.setPoliticsType(rs.getInt(5));
                    student.setEmail(rs.getString(6));
                    student.setTel(rs.getString(7));
                    student.setScore(rs.getInt(8));
                    student.setFirstChooseId(rs.getInt(9));
                    student.setSecondChooseId(rs.getInt(10));
                    student.setAdmissionSituation(rs.getInt(11));
                }
                return student;
            }
        }, studentNo);
    }

    @Override
    public List<Student> findAll() throws SQLException {
        String sql = "select * from student";
        return (List<Student>) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                List<Student> studentList = new ArrayList<Student>();
                Student student = null;
                while (rs.next()) {
                    student = new Student();
                    student.setStudentNo(rs.getString(1));
                    student.setInstituteId(rs.getInt(2));
                    student.setStudentName(rs.getString(3));
                    student.setSex(rs.getString(4));
                    student.setPoliticsType(rs.getInt(5));
                    student.setEmail(rs.getString(6));
                    student.setTel(rs.getString(7));
                    student.setScore(rs.getInt(8));
                    student.setFirstChooseId(rs.getInt(9));
                    student.setSecondChooseId(rs.getInt(10));
                    student.setAdmissionSituation(rs.getInt(11));
                    studentList.add(student);
                }
                return studentList;
            }
        });
    }

    @Override
    public List<Student> findAllByChoose(String chooseNum) throws SQLException {
        String sql = "select * from student where admissionSituation is null and "+chooseNum+" is not null;";
        return (List<Student>) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                List<Student> studentList = new ArrayList<Student>();
                Student student = null;
                while (rs.next()) {
                    student = new Student();
                    student.setStudentNo(rs.getString(1));
                    student.setStudentName(rs.getString(3));
                    student.setEmail(rs.getString(6));
                    student.setTel(rs.getString(7));
                    student.setScore(rs.getInt(8));
                    if (chooseNum.equals("firstChooseId")) {
                        student.setFirstChooseId(rs.getInt(9));
                    } else if (chooseNum.equals("secondChooseId")) {
                        student.setSecondChooseId(rs.getInt(10));
                    }
                    studentList.add(student);
                }
                return studentList;
            }
        });
    }
}

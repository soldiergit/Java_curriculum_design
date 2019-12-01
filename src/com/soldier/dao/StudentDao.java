package com.soldier.dao;

import com.soldier.entitys.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/6 21:35
 * @Desc: 学生类DAO
 */
public interface StudentDao {

    //增加方法：用于管理员
    public void add(Student student) throws SQLException;

    //更新录取状态：被老师录取之后，1-第一志愿被录取；2-第二志愿被录取
    public void updateAdmissionSituation(int admissionSituation, String studentNo) throws SQLException;

    //更新方法：用于学生
    public void update(Student student) throws SQLException;

    //学生填报第一志愿
    public void addFirstChoose(int subjectId,String studentNo) throws SQLException;

    //学生填报第二志愿
    public void addSecondChoose(int subjectId,String studentNo) throws SQLException;

    //学生查看录取情况
    public int admissionSituation(String studentNo) throws SQLException;

    //删除方法
    public void delete(String studentNo) throws SQLException;

    //查找方法
    public Student findById(String studentNo) throws SQLException;

    //查找所有
    public List<Student> findAll() throws SQLException;

    //查找所有填报的志愿
    public List<Student> findAllByChoose(String chooseNum) throws SQLException;

}

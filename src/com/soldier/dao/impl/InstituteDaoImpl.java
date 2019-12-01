package com.soldier.dao.impl;

import com.soldier.dao.InstituteDao;
import com.soldier.db.JdbcTemplete;
import com.soldier.entitys.Institute;
import com.soldier.handler.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 兵哥哥
 * @Date: 2018/12/6 21:41
 * @Desc: 学院类DAO实现
 */
public class InstituteDaoImpl implements InstituteDao {

    private JdbcTemplete jdbcTemplete;
    public InstituteDaoImpl() {
        jdbcTemplete = new JdbcTemplete();
    }

    /**
     * 增加方法
     */
    @Override
    public void add(Institute institute) throws SQLException {
        String sql = "insert into institute(instituteName)values(?)";
        jdbcTemplete.updete(sql, institute.getInstituteName());
    }

    /**
     * 更新方法
     */
    @Override
    public void update(Institute institute) throws SQLException {
        String sql = "update institute set instituteName=? where instituteId=?";
        jdbcTemplete.updete(sql,institute.getInstituteName(),institute.getInstituteId());
    }

    /**
     * 删除方法
     */
    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from institute where instituteId=?";
        jdbcTemplete.updete(sql,id);
    }

    /**
     * 根据ID查找数据
     */
    @Override
    public Institute findById(int id) throws SQLException {
        String sql = "select * from institute where instituteId=?";
        return (Institute) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                Institute institute = null;
                if (rs.next()) {
                    institute = new Institute();
                    institute.setInstituteId(id);
                    institute.setInstituteName(rs.getString(2));
                }
                return institute;
            }
        } , id);  //记得把ID传过去
    }

    /**
     * 查找所有
     */
    @Override
    public List<Institute> findAll() throws SQLException {
        String sql = "select * from institute";
        return (List<Institute>) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                List<Institute> instituteList = new ArrayList<Institute>();
                Institute institute = null;
                while (rs.next()) {
                    institute = new Institute();
                    institute.setInstituteId(rs.getInt(1));
                    institute.setInstituteName(rs.getString(2));
                    instituteList.add(institute);
                }
                return instituteList;
            }
        });
    }
}

package com.soldier.dao.impl;

import com.soldier.dao.TitleDao;
import com.soldier.db.JdbcTemplete;
import com.soldier.entitys.Title;
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
public class TitleDaoImpl implements TitleDao {

    private JdbcTemplete jdbcTemplete;
    public TitleDaoImpl() {
        jdbcTemplete = new JdbcTemplete();
    }

    /**
     * 增加方法
     */
    @Override
    public void add(Title title) throws SQLException {
        String sql = "insert into title(titleName)values(?)";
        jdbcTemplete.updete(sql, title.getTitleName());
    }

    /**
     * 更新方法
     */
    @Override
    public void update(Title title) throws SQLException {
        String sql = "update title set titleName=? where titleId=?";
        jdbcTemplete.updete(sql,title.getTitleName(),title.getTitleId());
    }

    /**
     * 删除方法
     */
    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from title where titleId=?";
        jdbcTemplete.updete(sql,id);
    }

    /**
     * 根据ID查找数据
     */
    @Override
    public Title findById(int id) throws SQLException {
        String sql = "select * from title where titleId=?";
        return (Title) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                Title title = null;
                if (rs.next()) {
                    title = new Title();
                    title.setTitleId(id);
                    title.setTitleName(rs.getString(2));
                }
                return title;
            }
        } , id);  //记得把ID传过去
    }

    /**
     * 查找所有
     */
    @Override
    public List<Title> findAll() throws SQLException {
        String sql = "select * from title";
        return (List<Title>) jdbcTemplete.query(sql, new ResultSetHandler() {
            @Override
            public Object doHandler(ResultSet rs) throws SQLException {
                List<Title> titleList = new ArrayList<Title>();
                Title title = null;
                while (rs.next()) {
                    title = new Title();
                    title.setTitleId(rs.getInt(1));
                    title.setTitleName(rs.getString(2));
                    titleList.add(title);
                }
                return titleList;
            }
        });
    }
}

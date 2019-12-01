package com.soldier.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultSetHandler--结构处理器
 *          base--基类    Handler--处理程序
 *   与com.hj.dao.PersonDao接口一样是接口类
 *     **其实接口也是一个类**
 */
public interface ResultSetHandler {

    public Object doHandler(ResultSet rs) throws SQLException;  //

}

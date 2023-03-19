package com.toast.common.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author 土司先生
 * @time 2023/1/20
 * @describe 数据库连接管理类
 */
public class DatabaseConnection {
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://127.0.0.1:3306/mvc_datadabase";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";
    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();
    private DatabaseConnection() {} // 构造方法私有化
    public static Connection rebuildConnection() {  // 重新创建数据库连接
        Connection conn = null ;
        try {
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    public static Connection getConnection() {
        Connection conn = THREAD_LOCAL.get(); // 获取当前线程中的连接对象
        if (conn == null) { // 没有连接对象
            conn = rebuildConnection(); // 建立数据库连接
            THREAD_LOCAL.set(conn); // 保存连接对象
        }
        return conn;
    }
    public static void close() {
        Connection conn = THREAD_LOCAL.get(); // 获取当前线程中的连接对象
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            THREAD_LOCAL.remove(); // 清空ThreadLocal
        }
    }
}


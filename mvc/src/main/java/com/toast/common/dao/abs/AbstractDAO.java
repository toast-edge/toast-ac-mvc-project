package com.toast.common.dao.abs;


import com.toast.common.mvc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO { // 这个类是一个不完整的类
    protected PreparedStatement pstmt; // 作为一个公共属性存在
    protected Connection connection; // 作为一个公共属性存在
    // 按照面向对象的设计原则，所有的子类在进行对象实例化时都一定要调用父类构造
    public AbstractDAO() {  // 无参构造方法
        this.connection = DatabaseConnection.getConnection(); // 获取数据库连接
    }
    public Long countHandle(String tableName) throws SQLException  {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        this.pstmt = this.connection.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }
    public Long countHandle(String tableName, String column, String keyword) throws SQLException  {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + column + " LIKE ?";
        this.pstmt = this.connection.prepareStatement(sql);
        this.pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }
}

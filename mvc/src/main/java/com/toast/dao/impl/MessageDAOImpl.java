package com.toast.dao.impl;


import com.toast.common.dao.abs.AbstractDAO;
import com.toast.common.mvc.annotation.Repository;
import com.toast.dao.IMessageDAO;
import com.toast.vo.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Repository // 此为数据层
public class MessageDAOImpl extends AbstractDAO implements IMessageDAO {
    // 本次只考虑数据的列表操作，而其他的数据层方法并没有使用，所以方法体全部为空
    @Override
    public boolean doCreate(Message message) throws SQLException {
        String sql = "INSERT INTO message(title, content) VALUES (?, ?)";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, message.getTitle());
        super.pstmt.setString(2, message.getContent());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doEdit(Message message) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> longs) throws SQLException {
        return false;
    }

    @Override
    public Message findById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Message> findAll() throws SQLException {
        List<Message> messageList = new ArrayList<>();
        String sql = "SELECT id, title, content FROM message";
        super.pstmt = super.connection.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Message msg = new Message();
            msg.setId(rs.getLong(1));
            msg.setTitle(rs.getString(2));
            msg.setContent(rs.getString(3));
            messageList.add(msg);
        }
        return messageList;
    }

    @Override
    public List<Message> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        List<Message> messageList = new ArrayList<>();
        String sql = "SELECT id, title, content FROM message LIMIT ?,?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setInt(1, (currentPage - 1) * lineSize);
        super.pstmt.setInt(2, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Message msg = new Message();
            msg.setId(rs.getLong(1));
            msg.setTitle(rs.getString(2));
            msg.setContent(rs.getString(3));
            messageList.add(msg);
        }
        return messageList;
    }

    @Override
    public List<Message> findSplit(Integer currentPage, Integer lineSize, String column, String keyword) throws SQLException {
        List<Message> messageList = new ArrayList<>();
        String sql = "SELECT id, title, content FROM message WHERE " + column + " LIKE ? LIMIT ?,?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyword + "%");
        super.pstmt.setInt(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Message msg = new Message();
            msg.setId(rs.getLong(1));
            msg.setTitle(rs.getString(2));
            msg.setContent(rs.getString(3));
            messageList.add(msg);
        }
        return messageList;
    }

    @Override
    public Long getAllCount() throws SQLException {
        return super.countHandle("message");
    }

    @Override
    public Long getAllCount(String column, String keyword) throws SQLException {
        return super.countHandle("message", column, keyword);
    }
}

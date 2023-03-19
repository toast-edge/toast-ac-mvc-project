package com.toast.dao.impl;


import com.toast.common.dao.abs.AbstractDAO;
import com.toast.common.mvc.annotation.Repository;
import com.toast.dao.IMemberDAO;
import com.toast.vo.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
@Repository
public class MemberDAOImpl extends AbstractDAO implements IMemberDAO { // 实现接口
    // AbastractDAO抽象类构造方法之中已经获取到了数据库的连接实例
    @Override
    public boolean doCreate(Member member) throws SQLException {
        String sql = "INSERT INTO member(mid,name,age,email,sex,birthday,note) " +
                " VALUES(?,?,?,?,?,?,?) ";
        super.pstmt = super.connection.prepareStatement(sql); // 实例化PreparedStatement接口
        super.pstmt.setString(1, member.getMid());
        super.pstmt.setString(2, member.getName());
        super.pstmt.setInt(3, member.getAge());
        super.pstmt.setString(4, member.getEmail());
        super.pstmt.setString(5, member.getSex());
        super.pstmt.setDate(6, new java.sql.Date(member.getBirthday().getTime()));
        super.pstmt.setString(7, member.getNote());
        return super.pstmt.executeUpdate() > 0; //' 有更新记录行数返回
    }

    @Override
    public boolean doEdit(Member member) throws SQLException {
        String sql = "UPDATE member SET name=?,age=?,email=?,sex=?,birthday=?,note=? WHERE mid=?";
        super.pstmt = super.connection.prepareStatement(sql); // 实例化PreparedStatement接口
        super.pstmt.setString(1, member.getName());
        super.pstmt.setInt(2, member.getAge());
        super.pstmt.setString(3, member.getEmail());
        super.pstmt.setString(4, member.getSex());
        super.pstmt.setDate(5, new java.sql.Date(member.getBirthday().getTime()));
        super.pstmt.setString(6, member.getNote());
        super.pstmt.setString(7, member.getMid());
        return super.pstmt.executeUpdate() > 0; //' 有更新记录行数返回
    }

    @Override
    public boolean doRemove(Set<String> strings) throws SQLException {
        StringBuffer sql = new StringBuffer(30); // SQL操作的拼凑
        sql.append("DELETE FROM member").append(" WHERE mid ").append(" IN (");
        strings.forEach((id) -> sql.append("?,")); // 设置占位符
        sql.delete(sql.length() - 1, sql.length()).append(")");
        super.pstmt = super.connection.prepareStatement(sql.toString());
        int foot = 1;
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            super.pstmt.setString(foot ++, iterator.next());
        }
        return super.pstmt.executeUpdate() == strings.size();
    }

    @Override
    public Member findById(String s) throws SQLException {
        Member vo = null; // 保存返回数据
        String sql = "SELECT mid,name,age,email,sex,birthday,note " +
                " FROM member WHERE mid=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, s);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {    // 数据存在
           vo = new Member();
           vo.setMid(rs.getString(1));
           vo.setName(rs.getString(2));
           vo.setAge(rs.getInt(3));
           vo.setEmail(rs.getString(4));
           vo.setSex(rs.getString(5));
           vo.setBirthday(rs.getDate(6));
           vo.setNote(rs.getString(7));
        }
        return vo;
    }

    @Override
    public List<Member> findAll() throws SQLException {
        List<Member> all = new ArrayList<>();
        String sql = "SELECT mid,name,age,email,sex,birthday,note FROM member";
        super.pstmt = super.connection.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setEmail(rs.getString(4));
            vo.setSex(rs.getString(5));
            vo.setBirthday(rs.getDate(6));
            vo.setNote(rs.getString(7));
            all.add(vo); // 数据保存在集合之中
        }
        return all;
    }

    @Override
    public List<Member> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        List<Member> all = new ArrayList<>();
        String sql = "SELECT mid,name,age,email,sex,birthday,note " +
                " FROM member LIMIT ?,?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setInt(1, (currentPage - 1) * lineSize);
        super.pstmt.setInt(2, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setEmail(rs.getString(4));
            vo.setSex(rs.getString(5));
            vo.setBirthday(rs.getDate(6));
            vo.setNote(rs.getString(7));
            all.add(vo); // 数据保存在集合之中
        }
        return all;
    }

    @Override
    public List<Member> findSplit(Integer currentPage, Integer lineSize, String column, String keyword) throws SQLException {
        List<Member> all = new ArrayList<>();
        String sql = "SELECT mid,name,age,email,sex,birthday,note " +
                " FROM member WHERE " + column + " LIKE ? LIMIT ?,?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyword + "%");
        super.pstmt.setInt(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setEmail(rs.getString(4));
            vo.setSex(rs.getString(5));
            vo.setBirthday(rs.getDate(6));
            vo.setNote(rs.getString(7));
            all.add(vo); // 数据保存在集合之中
        }
        return all;
    }

    @Override
    public Long getAllCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM member";
        super.pstmt = super.connection.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L; // 没有记录返回0
    }

    @Override
    public Long getAllCount(String column, String keyword) throws SQLException {
        String sql = "SELECT COUNT(*) FROM member WHERE " + column + " LIKE ?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0L; // 没有记录返回0
    }

    @Override
    public Member findByEmail(String email) throws SQLException {
        Member vo = null; // 保存返回数据
        String sql = "SELECT mid,name,age,email,sex,birthday,note " +
                " FROM member WHERE email=?";
        super.pstmt = super.connection.prepareStatement(sql);
        super.pstmt.setString(1, email);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {    // 数据存在
            vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setEmail(rs.getString(4));
            vo.setSex(rs.getString(5));
            vo.setBirthday(rs.getDate(6));
            vo.setNote(rs.getString(7));
        }
        return vo;
    }
}

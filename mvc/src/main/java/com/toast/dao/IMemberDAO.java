package com.toast.dao;

import com.toast.common.dao.base.IBaseDAO;
import com.toast.vo.Member;

import java.sql.SQLException;

public interface IMemberDAO extends IBaseDAO<String, Member> { // 数据层接口
    /**
     * 根据Email地址获取用户信息
     * @param email 要查询的email
     * @return 如果有对应的email数据，则以VO对象的形式封装返回，如果没有则返回null
     * @throws SQLException 数据库执行时所抛出的JDBC异常
     */
    public Member findByEmail(String email) throws SQLException;
}

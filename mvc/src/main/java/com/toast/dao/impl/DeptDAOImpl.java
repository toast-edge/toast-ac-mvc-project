package com.toast.dao.impl;

import com.toast.common.dao.abs.AbstractDAO;
import com.toast.common.dao.base.IBaseDAO;
import com.toast.common.mvc.annotation.Repository;
import com.toast.dao.IDeptDAO;
import com.toast.vo.Dept;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/18
 * @describe
 */
@Repository
public class DeptDAOImpl extends AbstractDAO  implements IDeptDAO {
    @Override
    public boolean doCreate(Dept dept) throws SQLException {
        return false;
    }

    @Override
    public boolean doEdit(Dept dept) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<Long> longs) throws SQLException {
        return false;
    }

    @Override
    public Dept findById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Dept> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Dept> findSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Dept> findSplit(Integer currentPage, Integer lineSize, String column, String keyword) throws SQLException {
        return null;
    }

    @Override
    public Long getAllCount() throws SQLException {
        return null;
    }

    @Override
    public Long getAllCount(String column, String keyword) throws SQLException {
        return null;
    }
}

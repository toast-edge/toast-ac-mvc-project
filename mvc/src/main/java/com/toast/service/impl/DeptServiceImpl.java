package com.toast.service.impl;

import com.toast.common.mvc.annotation.Aspect;
import com.toast.common.mvc.annotation.Autowired;
import com.toast.common.mvc.annotation.Service;
import com.toast.dao.IDeptDAO;
import com.toast.service.IDeptService;
import com.toast.vo.Dept;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/18
 * @describe
 */
@Service
@Aspect
public class DeptServiceImpl implements IDeptService {
    @Autowired
    private IDeptDAO dao;
    @Override
    public List<Dept> list() throws Exception {
        return dao.findAll();
    }

    @Override
    public boolean add(Dept dept) throws Exception {
        return dao.doCreate(dept);
    }

    @Override
    public boolean addBatch(List<Dept> dept) throws Exception {
        for (Dept d : dept) {
            dao.doCreate(d);
        }
        return true;
    }

    @Override
    public List<Dept> split(int currentPage, int lineSize, String column, String keyword) throws Exception {
        return dao.findSplit(currentPage, lineSize, column, keyword);
    }
}

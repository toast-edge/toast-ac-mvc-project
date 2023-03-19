package com.toast.service.impl;

import com.toast.common.mvc.annotation.Aspect;
import com.toast.common.mvc.annotation.Autowired;
import com.toast.common.mvc.annotation.Service;
import com.toast.common.service.abs.AbstractService;
import com.toast.dao.IMemberDAO;
import com.toast.service.IMemberService;
import com.toast.vo.Member;

import java.util.List;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/18
 * @describe
 */
@Service
@Aspect
public class MemberServiceImpl extends AbstractService implements IMemberService {
    @Autowired
    private IMemberDAO dao;
    @Override
    public List<Member> list() throws Exception {
        return dao.findAll();
    }

    @Override
    public boolean add(Member member) throws Exception {
        return dao.doCreate(member);
    }

    @Override
    public boolean addBatch(List<Member> member) throws Exception {
        for (Member m : member) {
            dao.doCreate(m);
        }
        return true;
    }

    @Override
    public List<Member> split(int currentPage, int lineSize, String column, String keyword) throws Exception {
        return dao.findSplit(currentPage, lineSize, column, keyword);
    }
}

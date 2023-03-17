package com.toast.service.impl;


import com.toast.common.mvc.annotation.Aspect;
import com.toast.common.mvc.annotation.Autowired;
import com.toast.common.mvc.annotation.Service;
import com.toast.common.service.abs.AbstractService;
import com.toast.dao.IMessageDAO;
import com.toast.service.IMessageService;
import com.toast.vo.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Aspect
public class MessageServiceImpl extends AbstractService implements IMessageService {
    @Autowired(name = "messageDAOImpl")
    private IMessageDAO messageDAO;
    @Override
    public List<Message> list() throws Exception {
        return this.messageDAO.findAll();
    }

    @Override
    public boolean addBatch(List<Message> messages) throws Exception {
        for (Message message : messages) {
            this.messageDAO.doCreate(message); // 数据存储
        }
        return true;
    }

    @Override
    public Map<String, Object> split(int currentPage, int lineSize, String column, String keyword) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (super.checkEmpty(column, keyword)) { // 数据不为空
            result.put("allMessages", this.messageDAO.findSplit(currentPage, lineSize, column, keyword));
            result.put("allRecorders", this.messageDAO.getAllCount(column, keyword));
        } else {
            result.put("allMessages", this.messageDAO.findSplit(currentPage, lineSize));
            result.put("allRecorders", this.messageDAO.getAllCount());
        }
        return result;
    }
}

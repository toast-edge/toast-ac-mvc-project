package com.toast.service;

import com.toast.vo.Message;

import java.util.List;
import java.util.Map;

public interface IMessageService {
    public List<Message> list() throws Exception;

    public boolean add(Message message) throws Exception;

    public boolean addBatch(List<Message> messages) throws Exception ; // 批量增加

    /**
     * 分页数据查询处理
     * @param currentPage 当前所在页码
     * @param lineSize 每页显示的数据行
     * @param column 模糊查询数据列
     * @param keyword 模糊查询内容
     * @return 返回的内容包含有统计数据以及List集合，所以对于Map集合之中的内容包含有两类信息：
     * 1、key = allMessages、value = List集合，全部的Message数据
     * 2、key = allRecorders、value = message数据表的行数统计
     * @throws Exception
     */
    public Map<String, Object> split(int currentPage, int lineSize, String column, String keyword) throws Exception;
}

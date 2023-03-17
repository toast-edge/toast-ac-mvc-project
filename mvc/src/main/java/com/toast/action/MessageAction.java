package com.toast.action;

import com.toast.common.mvc.annotation.Controller;
import com.toast.common.mvc.annotation.RequestMapping;

/**
 * @author 土司先生
 * @time 2023/1/16
 * @describe
 */
@Controller // 表示当前的类是一个控制器处理类
@RequestMapping("/pages/message/")
public class MessageAction {
    @RequestMapping("add")
    public void add() {
        System.out.println("【MessageAction.add()】 增加消息内容");
    }

    @RequestMapping("list")
    public void list() {
        System.out.println("【MessageAction.list()】 列表消息内容");
    }

    @RequestMapping("edit")
    public void edit() {
        System.out.println("【MessageAction.edit()】 修改消息内容");
    }

    @RequestMapping("remove")
    public void remove() {
        System.out.println("【MessageAction.remove()】 删除消息内容");
    }
}

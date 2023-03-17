package com.toast.action;

import com.toast.common.mvc.annotation.Controller;
import com.toast.common.mvc.annotation.RequestMapping;

/**
 * @author 土司先生
 * @time 2023/3/18
 * @describe
 */
@Controller
@RequestMapping("/message/")
public class MessageAction {
    @RequestMapping("add")
    public void add() {
        System.out.println("【MessageAction】 + 新增消息内容");
    }

    @RequestMapping("remove")
    public void remove() {
        System.out.println("【MessageAction】 + 删除消息内容");
    }

    @RequestMapping("edit")
    public void edit() {
        System.out.println("【MessageAction】 + 修改消息内容");
    }

    @RequestMapping("list")
    public void list() {
        System.out.println("【MessageAction】 + 消息列表内容");
    }
}

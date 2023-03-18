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
        System.out.println("【ADD】新增内容消息");
    }
    public void remove() {}
    public void edit() {}
    public void list() {}
}

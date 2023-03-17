package com.toast.action;

import com.toast.common.mvc.annotation.Controller;
import com.toast.common.mvc.annotation.RequestMapping;

/**
 * @author 土司先生
 * @time 2023/1/16
 * @describe
 */
@Controller // 表示当前的类是一个控制器处理类
@RequestMapping("/pages/dept/")
public class EmpAction {
    @RequestMapping("add")
    public void add() {
        System.out.println("【EmpAction.add()】 增加雇员信息");
    }

    @RequestMapping("list")
    public void list() {
        System.out.println("【EmpAction.add()】 列表雇员信息");
    }

    @RequestMapping("edit")
    public void edit() {
        System.out.println("【EmpAction.edit()】 修改雇员信息");
    }

    @RequestMapping("remove")
    public void remove() {
        System.out.println("【EmpAction.remove()】 删除雇员信息");
    }
}

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
public class DeptAction {
    @RequestMapping("add")
    public void add() {
        System.out.println("【DeptAction.add()】 增加部门信息");
    }

    @RequestMapping("list")
    public void list() {
        System.out.println("【DeptAction.add()】 列表部门信息");
    }

    @RequestMapping("edit")
    public void edit() {
        System.out.println("【DeptAction.edit()】 修改部门信息");
    }

    @RequestMapping("remove")
    public void remove() {
        System.out.println("【DeptAction.remove()】 删除部门信息");
    }
}

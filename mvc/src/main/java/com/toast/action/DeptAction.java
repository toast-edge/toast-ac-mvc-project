package com.toast.action;

import com.toast.common.action.abs.AbstractAction;
import com.toast.common.mvc.annotation.Autowired;
import com.toast.common.mvc.annotation.Controller;
import com.toast.common.mvc.annotation.RequestMapping;
import com.toast.service.IDeptService;
import com.toast.service.IMemberService;
import com.toast.vo.Dept;
import com.toast.vo.Member;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/18
 * @describe
 */
@Controller
@RequestMapping("/dept/")
public class DeptAction extends AbstractAction {
    @Autowired
    private IDeptService service;

    @RequestMapping("add")
    public void add() {
        try {
            service.add(new Dept(
                    Math.round(4),
                    "土司先生-销售部",
                    10,
                    3,
                    "司先生--50-B"
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("remove")
    public void remove() {
        System.out.println("【remove】删除内容消息");
    }
    @RequestMapping("edit")
    public void edit() {
        System.out.println("【edit】修改内容消息");
    }

    @RequestMapping("list")
    public List<Dept> list() {
        try {
            return service.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package com.toast.action;

import com.toast.common.action.abs.AbstractAction;
import com.toast.common.mvc.annotation.Autowired;
import com.toast.common.mvc.annotation.Controller;
import com.toast.common.mvc.annotation.RequestMapping;
import com.toast.common.servlet.ModelAndView;
import com.toast.dao.IMemberDAO;
import com.toast.service.IMemberService;
import com.toast.service.IMessageService;
import com.toast.vo.Member;

import java.util.Arrays;
import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/18
 * @describe
 */
@Controller
@RequestMapping("/member/")
public class MemberAction extends AbstractAction {

    @Autowired
    private IMemberService service;

    @RequestMapping("add")
    public void add(Member member, String[] urls) {
        try {
            System.out.println(member);
            System.out.println(Arrays.asList(urls));
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
    public ModelAndView list() {
        try {
            return new ModelAndView("/dept/dept_list.jsp", "list", service.list());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

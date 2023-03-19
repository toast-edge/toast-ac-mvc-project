package com.toast.test;

import com.toast.action.MemberAction;
import com.toast.common.mvc.util.MethodParameterUtil;

import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/18
 * @describe
 */
public class TestActionMethodSpring {
    public static void main(String[] args) {
        Class<?> clazz = TestActionMethodSpring.class;
        Map<String, Class> map = MethodParameterUtil.getMethodParameter(clazz, "test");
        for (Map.Entry<String, Class> entry : map.entrySet()) {
            System.out.println("参数名称： " + entry.getKey() + "，参数类型：" + entry.getValue());
        }
    }

    public void test(int age, String name, Long id) {

    }
}

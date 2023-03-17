package com.toast.common.mvc.bean;

import java.lang.reflect.Method;

/**
 * @author 土司先生
 * @time 2023/1/16
 * @describe 控制层的数据关联
 * 同一个Action之中的处理方法有可能会存在有若干个Method,每一个MEthod映射要有一个此类对象包装
 */
public class ControllerRequestMapping {
    private Class<?> actionClass; // 保存匹配的Action类的信息
    private Method actionMethod; // 保存映射的访问

    public ControllerRequestMapping(Class<?> actionClass, Method actionMethod) {
        this.actionClass = actionClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getActionClass() {
        return actionClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}

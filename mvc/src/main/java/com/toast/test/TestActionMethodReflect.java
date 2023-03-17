package com.toast.test;

import com.toast.action.MessageAction;

import java.lang.reflect.Method;

/**
 * @author 土司先生
 * @time 2023/1/20
 * @describe
 */
public class TestActionMethodReflect {
    public static void main(String[] args) {
        Class<?> clazz = MessageAction.class; // 获取Class实例
        // 如果要想使用getMethod()方法获取一个方法的实例，那么首先就一定要获取方法的参数列表
        Method actionMethod = getMethodByName(clazz, "add"); // 获取指定的方法对象
        System.out.println(actionMethod); // public void com.yootk.action.MessageAction.add(java.lang.String,java.lang.String)
        // 此时实际上是无法清楚的获取方法的参数的名称，而能够获取的仅仅是方法参数的类型
        Class<?> types [] = actionMethod.getParameterTypes(); // 获取参数的类型
        for (Class<?> type : types) {
            System.out.println(type);
        }
    }

    public static Method getMethodByName(Class<?> clazz, String methodName) {
        Method methods[] = clazz.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {  // 名称相符合
                return method;
            }
        }
        return null;
    }
}


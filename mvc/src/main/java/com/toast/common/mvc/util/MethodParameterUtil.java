package com.toast.common.mvc.util;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/18
 * @describe 参数解析工具类
 */
public class MethodParameterUtil {
    private MethodParameterUtil() {}

    /**
     * 根据名称获取指定Method类对象实例
     * @param clazz 要进行处理操作的Class实例
     * @param methodName 方法名称
     * @return  Method实例，如果不存在则返回null
     */
    public static Method getMethodByName(Class<?> clazz, String methodName) {
        Method methods[] = clazz.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) { // 名称符合
                return method;
            }
        }
        return null;
    }

    /**
     * 根据Method对象实例来解析方法的参数信息
     * @param clazz 要解析的Class实例
     * @param method Method对象实例
     * @return 所有的方法参数通过Map集合保存，KEY为参数名称、VALUE为参数类型
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Class> getMethodParameter(Class<?> clazz, Method method) {
        // 参数是有顺序的，不同的参数有不同的类型，所以参数的顺序很重要
        Map<String, Class> map = new LinkedHashMap<>(); // 链表实现，属于有顺序的保存
        if (method == null) {
            return map;
        }
        // 获取全部的方法中的参数名称，这个时候是按照参数定义的顺序返回的数组内容
        String names[] = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        Class<?> parameterTypes[] = method.getParameterTypes();
        for (int x = 0; x < parameterTypes.length; x ++) {
            map.put(names[x], parameterTypes[x]); // 参数名称以及参数类型
        }
        return map;
    }

    /**
     * 根据指定名称的方法实现方法参数的结构解析
     * @param clazz 要解析的Class实例
     * @param methodName 方法名称
     * @return 所有的方法参数通过Map集合保存， KEY - 参数名称， VALUE - 参数类型
     */
    public static Map<String, Class> getMethodParameter(Class<?> clazz, String methodName) {
        if (clazz == null  || methodName == null || "".equals(methodName)) {
            return Map.of();
        }
        return getMethodParameter(clazz, getMethodByName(clazz, methodName));
    }
}

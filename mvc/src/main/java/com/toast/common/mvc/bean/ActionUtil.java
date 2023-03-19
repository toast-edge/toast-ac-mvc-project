package com.toast.common.mvc.bean;

import com.toast.common.mvc.util.DataTypeConverterUtil;
import com.toast.common.mvc.util.MethodParameterUtil;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/19
 * @describe
 */
public class ActionUtil {
    private static final String UPLOAD_METHOD_NAME = "getUploadPath"; // 固定方法名称

    /**
     * 获取指定Action中的方法的参数内容，参数内容以数组形式存储
      * @param actionObject 要进行反射调用的Action类
     * @param method 要调用的方法，这个方法可以分析出参数的结构
     * @return 方法的数组内容
     */
    public static Object[] getMethodParameterValue(Object actionObject, Method method) {
        Object result[] = null; // 最终的返回结果
        Map<String, Class> params = MethodParameterUtil.getMethodParameter(actionObject.getClass(), method);
        if (params.size() == 0) {   // 当前的Action方法没有参数
            result = new Object[] {}; // 空数组返回
        } else {    // 方法上有参数
            result = new Object[params.size()]; // 数组的长度就是参数的个数
            int foot = 0; // 进行数组的下标控制
            for (Map.Entry<String, Class> entry : params.entrySet()) {  // 参数名称以及参数类型
                if (isBasic(entry.getValue())) { // 当前所传递的是一个普通类型
                    result[foot++] = DataTypeConverterUtil.convert(entry.getKey(), entry.getValue());
                } else if (isArray(entry.getValue())) { // 判断是否为数组类型
                    result[foot ++] = DataTypeConverterUtil.convertArray(entry.getKey(), entry.getValue());
                } else { // 当前操作的类型是一个VO实例
                    Object vo = null;
                    try {
                        vo = entry.getValue().getDeclaredConstructor().newInstance(); // 反射实例化对象
                        DataTypeConverterUtil.setObjectFieldValue(vo); // 设置对象中的属性信息
                    } catch (Exception e) {}
                    result[foot ++] = vo;
                }
            }
        }
        return result;
    }

    /**
     * 判断当前操作类型是否为基本普通类型
     * @param type  当前类型
     * @return
     */
    private static boolean isBasic(Class<?> type) {
        return "long".equals(type.getName()) ||
                "java.lang.Long".equals(type.getName())||
                "int".equals(type.getName()) ||
                "java.lang.Integer".equals(type.getName()) ||
                "double".equals(type.getName()) ||
                "java.lang.Double".equals(type.getName()) ||
                "boolean".equals(type.getName()) ||
                "java.lang.Boolean".equals(type.getName()) ||
                "java.lang.String".equals(type.getName()) ||
                "java.util.Date".equals(type.getName());
    }

    /**
     * 判断当前操作类型是否为数组类型
     * @param type  当前类型
     * @return
     */
    private static boolean isArray(Class<?> type) {
        return "int[]".equals(type.getTypeName()) ||
                "long[]".equals(type.getTypeName()) ||
                "java.lang.String[]".equals(type.getTypeName()) ||
                "java.lang.Integer[]".equals(type.getTypeName()) ||
                "java.lang.Long[]".equals(type.getTypeName());
    }
    /**
     * 根据Action类的实例获取上传路径
     * @param actionObject  控制层的action类
     * @return  返回指定的action类的上传路径
     */
    public static String getUpload(Object actionObject) {
        String path = "/upload"; // 设置一个默认的路径项
        // 根据方法名称获取方法
        Method method = MethodParameterUtil.getMethodByName(actionObject.getClass(), UPLOAD_METHOD_NAME);
        if (method != null) {
            try {
                path = (String) method.invoke(actionObject);
            } catch (Exception e) {}
        }
        return path;
    }
}

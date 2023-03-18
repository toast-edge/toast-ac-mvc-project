package com.toast.common.util;

/**
 * @author 土司先生
 * @time 2023/1/20
 * @describe 实现字符串的相关处经理
 */
public class StringUtil {
    // 构造方法私有化
    private StringUtil() {}

    /**
     * 传入的字符串首字母小写
     * @param str   首字母要小写的字符串
     * @return  返回一个首字母小写的字符串
     */
    public static String firstLower(String str) {   // 首字母小写
        if (str == null || "".equals(str)) {
            return str;
        }
        if (str.length() == 1) {
            return str.toLowerCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1); //
    }
}


package com.toast.common.action.abs;

/**
 * @author 土司先生
 * @time 2023/3/19
 * @describe 控制层公共抽象类
 */
public abstract class AbstractAction {
    public String getUploadPath() { // 是为了上传准备的
        return "/upload/";
    }
}

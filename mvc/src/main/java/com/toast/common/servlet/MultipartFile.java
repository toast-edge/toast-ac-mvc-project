package com.toast.common.servlet;

import java.io.File;

/**
 * @author 土司先生
 * @time 2023/1/20
 * @describe
 */
public class MultipartFile extends File { // 自定义的文件类
    private String contentType; // 文件的MIME类型
    private String originFileName; // 文件原始名称
    public MultipartFile(String path) {
        super(path);
    }
    public MultipartFile(File parent, String child) {
        super(parent, child);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    @Override
    public String toString() {
        return "【MultipartFile】" + super.toString() + "，contentType = " + this.contentType + "、originFileName = " + this.originFileName;
    }
}


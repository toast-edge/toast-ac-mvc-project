package com.toast.vo;

import java.io.Serializable;

/**
 * @author 土司先生
 * @time 2023/1/20
 * @describe
 */
public class Message implements Serializable {
    private Long id;
    private String title;
    private String content;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "【Message】id = " + this.id + "、title = " + this.title + "、content = " + this.content;
    }
}
package com.toast.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 土司先生
 * @time 2023/1/20
 * @describe
 */
public class Member implements Serializable {
    private String mid;
    private String name;
    private Integer age;
    private String email;
    private String sex;
    private Date birthday;
    private String note;

    public Member() {
    }

    public Member(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Member{" +
                "mid='" + mid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", note='" + note + '\'' +
                '}';
    }
}


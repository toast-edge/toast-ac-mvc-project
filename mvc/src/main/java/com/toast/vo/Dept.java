package com.toast.vo;

/**
 * @author 土司先生
 * @time 2023/3/18
 * @describe
 */
public class Dept {
    private long deptno;
    private String dname;
    private int bound;
    private long empno;
    private String mname;

    public Dept(long deptno, String dname, int bound, long empno, String mname) {
        this.deptno = deptno;
        this.dname = dname;
        this.bound = bound;
        this.empno = empno;
        this.mname = mname;
    }

    public long getDeptno() {
        return deptno;
    }

    public void setDeptno(long deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getBound() {
        return bound;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    public long getEmpno() {
        return empno;
    }

    public void setEmpno(long empno) {
        this.empno = empno;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", bound=" + bound +
                ", empno=" + empno +
                ", mname='" + mname + '\'' +
                '}';
    }
}

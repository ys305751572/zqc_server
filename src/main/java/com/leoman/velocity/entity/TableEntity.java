package com.leoman.velocity.entity;

import java.util.List;

/**
 *
 * Created by yesong on 2016/6/30.
 */
public class TableEntity {

    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private Boolean c5;

    private List<DDSub> list;

    public List<DDSub> getList() {
        return list;
    }

    public void setList(List<DDSub> list) {
        this.list = list;
    }

    public String getC1() {
        return c1;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public String getC2() {
        return c2;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public String getC3() {
        return c3;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public String getC4() {
        return c4;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }

    public Boolean getC5() {
        return c5;
    }

    public void setC5(Boolean c5) {
        this.c5 = c5;
    }

    @Override
    public String toString() {
        return "c1:" + c1 + "==c2:" + c2 + "==c3:" + c3 + "==c4:" + c4 + "==c5:" + c5;
    }
}

package com.ch.wchhuangya.dzah.tuicool.model;

/**
 * 与文章翻页有关的内容
 * Created by wchya on 2016-11-24 13:51
 */

public class ArticlePage {
    private int pageNo;
    private long firstTime;
    private String firstId;
    private String lastId;

    public ArticlePage() {
        setPageNo(0);
        setFirstTime(-1);
    }

    public void clear() {
        setPageNo(0);
        setFirstTime(-1);
        setFirstId("");
        setLastId("");
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public long getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(long firstTime) {
        this.firstTime = firstTime;
    }

    public String getFirstId() {
        return firstId;
    }

    public void setFirstId(String firstId) {
        this.firstId = firstId;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }
}

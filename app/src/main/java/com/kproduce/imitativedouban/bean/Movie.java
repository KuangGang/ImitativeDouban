package com.kproduce.imitativedouban.bean;

import java.io.Serializable;

/**
 * @author kuanggang
 */
public class Movie implements Serializable {
    private String title;
    private String secTitle;
    private String desc;
    private String info;
    private int coverId;

    public Movie(String title, String secTitle, String desc, int coverId, String info) {
        this.title = title;
        this.secTitle = secTitle;
        this.desc = desc;
        this.coverId = coverId;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public String getSecTitle() {
        return secTitle;
    }

    public String getDesc() {
        return desc;
    }

    public int getCoverId() {
        return coverId;
    }

    public String getInfo() {
        return info;
    }
}

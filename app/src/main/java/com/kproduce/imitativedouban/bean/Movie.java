package com.kproduce.imitativedouban.bean;

import java.io.Serializable;

/**
 * @author kuanggang
 */
public class Movie implements Serializable {
    private String title;
    private String secTitle;
    private String desc;
    private int coverId;

    public Movie(String title, String secTitle, String desc, int coverId) {
        this.title = title;
        this.secTitle = secTitle;
        this.desc = desc;
        this.coverId = coverId;
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
}

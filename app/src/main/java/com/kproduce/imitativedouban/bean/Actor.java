package com.kproduce.imitativedouban.bean;

import java.io.Serializable;

/**
 * @author kuanggang
 */
public class Actor implements Serializable {
    private String name;
    private String desc;
    private int coverId;

    public Actor(String name, String desc, int coverId) {
        this.name = name;
        this.desc = desc;
        this.coverId = coverId;
    }

    public String getName() {
        return name;
    }

    public int getCoverId() {
        return coverId;
    }

    public String getDesc() {
        return desc;
    }
}

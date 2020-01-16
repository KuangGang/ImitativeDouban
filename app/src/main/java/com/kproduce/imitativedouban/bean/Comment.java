package com.kproduce.imitativedouban.bean;

import java.io.Serializable;

/**
 * @author kuanggang
 */
public class Comment implements Serializable {
    private String name;
    private String time;
    private String comment;
    private int avatarId;

    public Comment(String name, String time, String comment, int avatarId) {
        this.name = name;
        this.time = time;
        this.comment = comment;
        this.avatarId = avatarId;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }

    public int getAvatarId() {
        return avatarId;
    }
}

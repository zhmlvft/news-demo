package com.zhm.model;

import java.io.Serializable;

/**
 * Created by zhm on 16-12-30.
 */
public class NewsType implements Serializable{
    private static final long serialVersionUID = -3565376144984015533L;
    private int id;
    private String channelId;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

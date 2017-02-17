package com.zmz.qzone.model;

import java.util.List;

/**
 * Created by Mzone on 2016/11/21.
 */

public class Message {
    private String name;
    private String pic_url;
    private String content;
    private String time;
    private List<PraiseUser> praise_members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<PraiseUser> getPraise_members() {
        return praise_members;
    }

    public void setPraise_members(List<PraiseUser> praise_members) {
        this.praise_members = praise_members;
    }
}

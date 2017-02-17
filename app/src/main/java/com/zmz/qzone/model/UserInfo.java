package com.zmz.qzone.model;

/**
 * Created by Mzone on 2016/10/27.
 * 用户
 */

public class UserInfo  {

    private String username;
    private String id;
    private String password;
    private String nick;
    private String avatar;
    private String cover;

    public UserInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return id;
    }
    public void setUserid(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                ", avatar='" + avatar + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}

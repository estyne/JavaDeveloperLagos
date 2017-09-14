package com.learnites.javadevelopers;

/**
 * Created by celestine on 14/09/2017.
 */

public class Devs {
    private String login, avatar_url,url;
    private String score;

    public Devs() {

    }

    public Devs(String login, String avatar_url, String url,String score) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.url = url;
        this.score = score;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar_url;
    }

    public void setAvatar(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}


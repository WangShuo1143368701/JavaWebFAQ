package com.lava.lavafaq.bean;

import java.util.Date;

public class Faq {
    private Integer id;

    private String username;

    private String mail;

    private String category1;

    private String category2;

    private String bugid;

    private String platform;

    private String phenomenon;

    private String editormd;

    private String editorhtml;

    private Date datetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1 == null ? null : category1.trim();
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2 == null ? null : category2.trim();
    }

    public String getBugid() {
        return bugid;
    }

    public void setBugid(String bugid) {
        this.bugid = bugid == null ? null : bugid.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon == null ? null : phenomenon.trim();
    }

    public String getEditormd() {
        return editormd;
    }

    public void setEditormd(String editormd) {
        this.editormd = editormd == null ? null : editormd.trim();
    }

    public String getEditorhtml() {
        return editorhtml;
    }

    public void setEditorhtml(String editorhtml) {
        this.editorhtml = editorhtml == null ? null : editorhtml.trim();
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

	@Override
	public String toString() {
		return "Faq [id=" + id + ", username=" + username + ", mail=" + mail
				+ ", category1=" + category1 + ", category2=" + category2
				+ ", bugid=" + bugid + ", platform=" + platform
				+ ", phenomenon=" + phenomenon + ", editormd=" + editormd
				+ ", editorhtml=" + editorhtml + ", datetime=" + datetime + "]";
	}
    
}
package com.soldier.entitys;

import java.util.Date;

public class User {

    private Integer userId;  //用户ID
    private String userName;  //用户名
    private String passWord;  //密码
    private Integer userType;  //用户类型
    private Date createdDate;  //创建日期
    private Date lastmodifyDate;  //最后修改日期

    public User(String userName, String passWord, Integer userType) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
    }

    public User(Integer userId, String userName, String passWord, Integer userType, Date createdDate, Date lastmodifyDate) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
        this.createdDate = createdDate;
        this.lastmodifyDate = lastmodifyDate;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastmodifyDate() {
        return lastmodifyDate;
    }

    public void setLastmodifyDate(Date lastmodifyDate) {
        this.lastmodifyDate = lastmodifyDate;
    }
}

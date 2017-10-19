package com.github.cherry.signature;

import com.github.cherry.signature.annotation.IgnoreSign;

public class User {
    private String userName;
    private String password;
    
    @IgnoreSign
    private String userIgnoreField;
    @IgnoreSign
    private String [] location;
    @IgnoreSign
    private Account account;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getUserIgnoreField() {
        return userIgnoreField;
    }

    public void setUserIgnoreField(String userIgnoreField) {
        this.userIgnoreField = userIgnoreField;
    }
}

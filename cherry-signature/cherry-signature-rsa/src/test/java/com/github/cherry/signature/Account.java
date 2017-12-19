package com.github.cherry.signature;

import com.github.cherry.signature.annotation.IgnoreSign;

public class Account {
    private String id;
    private Double balance;
    
    private double dd;
    
    public double getDd() {
        return dd;
    }
    public void setDd(double dd) {
        this.dd = dd;
    }
    @IgnoreSign
    private String accountIgnoreField;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public String getAccountIgnoreField() {
        return accountIgnoreField;
    }
    public void setAccountIgnoreField(String accountIgnoreField) {
        this.accountIgnoreField = accountIgnoreField;
    }
}

package com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset;

public class PartnerDataset {
    String fullname;
    String email;
    String lastpaid;
    String paymethod;
    String phonenumber;
    String expireDate;
    String payerID;
    String orderID;

    public PartnerDataset() {
    }

    public PartnerDataset(String fullname, String email, String lastpaid, String paymethod, String phonenumber, String expireDate, String payerID, String orderID) {
        this.fullname = fullname;
        this.email = email;
        this.lastpaid = lastpaid;
        this.paymethod = paymethod;
        this.phonenumber = phonenumber;
        this.expireDate = expireDate;
        this.payerID = payerID;
        this.orderID = orderID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastpaid() {
        return lastpaid;
    }

    public void setLastpaid(String lastpaid) {
        this.lastpaid = lastpaid;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPayerID() {
        return payerID;
    }

    public void setPayerID(String payerID) {
        this.payerID = payerID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}

package com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset;

public class PayRequestDataset
{
    String ammo;
    String email;
    String fullname;
    String phone;
    String status;
    String time;

    public String getAmmo() {
        return ammo;
    }

    public void setAmmo(String ammo) {
        this.ammo = ammo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public PayRequestDataset() {
    }

    public PayRequestDataset(String ammo, String email, String fullname, String phone, String status, String time) {
        this.ammo = ammo;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.status = status;
        this.time = time;
    }
}

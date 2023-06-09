package com.verzeo.medicinemajorproject;

public class UserHelperClassUser {

     String name, phone, adhar, email;
    public UserHelperClassUser() {
    }

    public UserHelperClassUser(String name, String phone, String adhar, String email) {
        this.name = name;
        this.phone = phone;
        this.adhar = adhar;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdhar() {
        return adhar;
    }

    public void setAdhar(String adhar) {
        this.adhar = adhar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

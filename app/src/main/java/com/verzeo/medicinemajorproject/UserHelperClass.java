package com.verzeo.medicinemajorproject;

public class UserHelperClass {

     String name,dose,date,time, adr;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String dose, String date, String time, String adr) {
        this.name = name;
        this.dose = dose;
        this.date = date;
        this.time = time;
        this.adr = adr;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

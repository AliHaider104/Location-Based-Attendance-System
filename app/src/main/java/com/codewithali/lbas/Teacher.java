package com.codewithali.lbas;

public class Teacher {
    String NAME;
    String CNIC;
    String PASSWORD;

    public Teacher()
    {

    }

    public Teacher(String n,String c,String p)
    {
        NAME=n;
        CNIC=c;
        PASSWORD=p;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}

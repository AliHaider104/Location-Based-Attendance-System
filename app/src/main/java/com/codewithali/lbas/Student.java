package com.codewithali.lbas;

public class Student {

    String NAME;
    String ROLL_NUMBER;
    String PASSWORD;
    String DEGREE;

    public Student()
    {

    }

    public Student(String name,String rollnumber,String password,String degree)
    {
        this.NAME=name;
        this.PASSWORD=password;
        this.ROLL_NUMBER=rollnumber;

    }

    public String getNAME() {
        return NAME;
    }

    public String getROLL_NUMBER() {
        return ROLL_NUMBER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getDEGREE() {
        return DEGREE;
    }
}

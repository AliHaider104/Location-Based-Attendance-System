package com.codewithali.lbas;

public class ClassData {

    String Date;
    String Teacher_CNIC;
    String Course_name;
    double Latitude;
    double Longitude;


    public ClassData()
    {

    }

    public ClassData(String d,String tc,String cn,double lat,double longitude)
    {
        Date=d;
        Teacher_CNIC=tc;
        Course_name=cn;
        Latitude=lat;
        Longitude=longitude;
    }



    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTeacher_CNIC() {
        return Teacher_CNIC;
    }

    public void setTeacher_CNIC(String teacher_CNIC) {
        Teacher_CNIC = teacher_CNIC;
    }

    public String getCourse_name() {
        return Course_name;
    }

    public void setCourse_name(String course_name) {
        Course_name = course_name;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        this.Longitude = longitude;
    }
}

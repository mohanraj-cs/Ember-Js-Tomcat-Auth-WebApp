/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.json;

/**
 *
 * @author Administrator
 */
public class TicketDetailsByPnr {
    	private int seatNo;
	private String classType;
	private String passName;
	private int age;    
	private String gender;
        private float fare;
        private String departTime;
        private String arrivalTime;
        private int busId;
        private String busName;

    public TicketDetailsByPnr(int seatNo, String classType, String passName, int age, String gender, float fare, String departTime, String arrivalTime, int busId, String busName) {
        this.seatNo = seatNo;
        this.classType = classType;
        this.passName = passName;
        this.age = age;
        this.gender = gender;
        this.fare = fare;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.busId = busId;
        this.busName = busName;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getPassName() {
        return passName;
    }

    public void setPassName(String passName) {
        this.passName = passName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }
    
}

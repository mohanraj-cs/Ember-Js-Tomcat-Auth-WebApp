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
public class ClassTypeDetails {
    private int classId;
    private String classType;
    private float fare;
    private String busName;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }
    
    public ClassTypeDetails(int classId, String classType, float fare, String busName) {
        this.classId = classId;
        this.classType = classType;
        this.fare = fare;
        this.busName = busName;
    }
    
}

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
public class Passenger {	
	private int seatNo;
	private String classType;
	private String passName;
	private int age;    
	private String gender;
        private float fare;

        public float getFare() {
            return fare;
        }

        public void setFare(float fare) {
            this.fare = fare;
        }

        public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
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

    public Passenger(int seatNo, String classType, String passName, int age, String gender, float fare) {
        this.seatNo = seatNo;
        this.classType = classType;
        this.passName = passName;
        this.age = age;
        this.gender = gender;
        this.fare = fare;
    }
        
   
}

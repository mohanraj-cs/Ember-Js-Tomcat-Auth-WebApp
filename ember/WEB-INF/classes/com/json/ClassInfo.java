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
public class ClassInfo
{
	private int classId;
	private String classType;
	private float fare;
	
	public ClassInfo(int classId, String classType, float fare) {
		super();
		this.classId = classId;
		this.classType = classType;
		this.fare = fare;
	}
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
	
}

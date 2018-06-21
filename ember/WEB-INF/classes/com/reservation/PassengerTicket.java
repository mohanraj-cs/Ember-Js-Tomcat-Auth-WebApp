/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reservation;

/**
 *
 * @author Administrator
 */
public class PassengerTicket {
	private int pnrId;
	private int routeId;
	
	private int userId;
	private float fare;
	public int getPnrId() {
		return pnrId;
	}
	public void setPnrId(int pnrId) {
		this.pnrId = pnrId;
	}
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public float getFare() {
		return fare;
	}
	public void setFare(float fare) {
		this.fare = fare;
	}
	public PassengerTicket(int pnrId, int routeId, int userId, float fare) {
		super();
		this.pnrId = pnrId;
		this.routeId = routeId;
		this.userId = userId;
		this.fare = fare;
	}
	
}

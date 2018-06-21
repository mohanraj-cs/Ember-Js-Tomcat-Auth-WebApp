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
public class PassengerTicketDetails {
    private int pnrId;
    private String source;
    private String destination;
    private float fare;

    public int getPnrId() {
        return pnrId;
    }

    public void setPnrId(int pnrId) {
        this.pnrId = pnrId;
    }

    public PassengerTicketDetails(int pnrId, String source, String destination, float fare) {
        this.pnrId = pnrId;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
    }
    

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }
    
}

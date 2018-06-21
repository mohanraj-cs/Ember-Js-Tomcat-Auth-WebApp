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
public class ACSemiSeaterSeats {
    private int seatNo;
    private String seatType="A/C Semi-Seater";
    private int classId;

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public ACSemiSeaterSeats(int seatNo, int classId) {
        this.seatNo = seatNo;
        this.classId = classId;
    }
    
}

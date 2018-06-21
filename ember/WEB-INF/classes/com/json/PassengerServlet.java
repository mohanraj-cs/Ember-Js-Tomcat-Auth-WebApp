/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.json;

import com.reservation.Reservation;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.servlet.http.HttpSession;  

import com.reservation.Reservation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class PassengerServlet extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    // This will store all received articles
    List<Article> articles = new LinkedList<Article>();
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        PrintWriter out=res.getWriter();
        String[] name=req.getParameterValues("passengerName[]");
        String[] sAge=req.getParameterValues("passengerAge[]");//string
        String[] gender=req.getParameterValues("passengerGender[]");
        ArrayList<Integer> age = new ArrayList<>();//integer
//        ArrayList<Integer> selectedSeats=req.getParameter("selectedSeats");
        System.out.println("com.json.PassengerServlet.doPost()");
        for(int i=0;i<name.length;i++)
        {
            System.out.println(name[i]+"  "+sAge[i]+"   "+gender[i]);
        }
        for(int i=0;i<sAge.length;i++)
        {
            age.add(Integer.valueOf(sAge[i]));
        }
        ArrayList<SeatsBooked> seatDetails=Reservation.readSeatsBooked();
        float fare=0,total=0;
        for(int i=0;i<seatDetails.size();i++)
        {
            fare=Reservation.readFare(seatDetails.get(i).getClassId());
            total+=fare;
        }
        System.out.println("##############################%%%%%%%%%%");
        String demo=(String) req.getSession(false).getAttribute("userName");
        System.out.println(demo);
        int userId=Integer.valueOf(demo);
        Reservation.insertPassengerTicketTable(Reservation.routeId, userId, total);
        System.out.println(fare);
        for(int i=0;i<seatDetails.size();i++)
        {
            Reservation.insertPassengerTable(name[i], age.get(i), gender[i], seatDetails.get(i).getSeatNo(), seatDetails.get(i).getSeatType());
            System.out.println(name[i]+age.get(i)+gender[i]+seatDetails.get(i).getSeatNo()+seatDetails.get(i).getSeatType());
        }
        
//        ArrayList<Boolean> seatsStaus=Reservation.readSeatsAvailablity(classId);
//        JSONObject jo=new JSONObject();
//        JSONArray ja=new JSONArray();
//        try {
//            jo.put("mohan", "asd");
//        } catch (JSONException ex) {
//            Logger.getLogger(RouteServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        ArrayList<Passenger> passengerDetails=Reservation.readPassengerTable();
        for(int i=0;i<passengerDetails.size();i++)
        {
            passengerDetails.get(i).setFare(total);
        }
//        JSONArray jsonArray=new JSONArray();
//        JSONArray arr=new JSONArray();
//        try {
//            for(int i=0;i<passengerDetails.size();i++)
//            {
//                JSONObject obj=new JSONObject();
//                obj.put("seatNo",String.valueOf( passengerDetails.get(i).getSeatNo()));
//                obj.put("classType", passengerDetails.get(i).getClassType());
//                obj.put("passName", passengerDetails.get(i).getPassName());
//                obj.put("age", String.valueOf(passengerDetails.get(i).getAge()));
//                obj.put("gender", passengerDetails.get(i).getGender());
//                
//                jsonArray.put(obj);
//                
//                        
//            }
//            
//            JSONObject obj=new JSONObject();
//            obj.put("details", jsonArray);
//            obj.put("fare",total);
//            arr.put(obj);
//            System.out.println(arr);
//        } 
//        catch (JSONException ex) {
//            Logger.getLogger(PassengerServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        out.println(arr);
        Reservation.flushSeats();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out,passengerDetails);
    }
}

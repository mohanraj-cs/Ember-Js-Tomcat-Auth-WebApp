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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONServlet2 extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    public static List<String> nameList;
    public static List<Integer> ageList;
    public static List<String> genderList;
    
    // This will store all received articles
 
    /***************************************************
     * URL: /jsonservlet
     * doPost(): receives JSON data, parse it, map it and send back as JSON
     ****************************************************/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
 
        System.out.println("com.json.JSONServlet.doPost()");
        // 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
 
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
        // 3. Convert received JSON to Article
        System.out.println("com.json.JSONServlet.doPost()");
        System.out.println(json);
        PassengerDetails obj=mapper.readValue(json, PassengerDetails.class);
        nameList.addAll(obj.getNameList());
        ageList.addAll(obj.getAgeList());
        genderList.addAll(obj.getGenderList());
        
    }
}

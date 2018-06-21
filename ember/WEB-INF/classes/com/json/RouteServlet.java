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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
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
public class RouteServlet extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    // This will store all received articles
    List<Article> articles = new LinkedList<Article>();
 
    /***************************************************
     * URL: /jsonservlet
     * doPost(): receives JSON data, parse it, map it and send back as JSON
     ****************************************************/
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        PrintWriter out=res.getWriter();
        System.out.println("##############################%%%%%%%%%%");
        String demo=(String) req.getSession(false).getAttribute("userName");
        System.out.println(demo);
        ArrayList<Route> routeList=Reservation.readRoute();
        JSONObject jo=new JSONObject();
        JSONArray ja=new JSONArray();
        try {
            jo.put("mohan", "asd");
        } catch (JSONException ex) {
            Logger.getLogger(RouteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, routeList);
            
//        out.println(jo);
// 
//        System.out.println("com.json.JSONServlet.doPost()");
//        // 1. get received JSON data from request
//        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String json = "";
//        if(br != null){
//            json = br.readLine();
//        }
// 
//        // 2. initiate jackson mapper
//        ObjectMapper mapper = new ObjectMapper();
// 
//        // 3. Convert received JSON to Article
//        System.out.println("com.json.JSONServlet.doPost()");
//        System.out.println(json);
//        BookedSets obj=mapper.readValue(json,BookedSets.class);
//        List<String> values=obj.getValues();
//        for(int i=0;i<values.size();i++)
//        {
//            System.out.println(values.get(i));
//        }
//        Article article = mapper.readValue(json, Article.class);
// 
//        // 4. Set response type to JSON
//        response.setContentType("application/json");            
//        
//        // 5. Add article to List<Article>
//        articles.add(article);
//        for(int i=0;i<articles.size();i++)
//        {
//            System.out.println(articles.get(i).getTitle());
//        }
//        // 6. Send List<Article> as JSON to client
//        mapper.writeValue(response.getOutputStream(), articles);
    }
}
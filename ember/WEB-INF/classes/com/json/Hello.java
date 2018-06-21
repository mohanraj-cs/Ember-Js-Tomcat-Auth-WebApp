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
public class Hello extends HttpServlet
{
public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
{
PrintWriter out=res.getWriter();
//String demo=(String)req.getSession(false).getAttribute("userName");
//out.println(demo);
//    System.out.println("yess    "+demo);
int userId=Integer.valueOf(req.getParameter("userId"));
String password=req.getParameter("password");
    int result=userValidation(userId, password);
    System.out.println("in java");
    System.out.println(userId);
    System.out.println(password);
    System.out.println(result);
    Reservation.userId=userId;
    out.println(result);
}

public static int userValidation(int userId,String password)
   {
       int res=0;
       Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM USER_INFO;" );
         int currId=0;
         String  currPassword=null;
         while ( rs.next() ) {
            currId = rs.getInt("userid");
            if(currId==userId)
            {
                currPassword = rs.getString("password");
                if(currPassword.equals(password))
                {
                    res=1;
                    break;
                }
            }
            
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       
       return res;
   }
}
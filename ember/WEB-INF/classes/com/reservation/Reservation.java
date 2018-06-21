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

import com.json.PassengerTicketDetails;
import com.json.Passenger;
import com.json.SeatsBooked;
import com.json.ClassInfo;
import com.json.Route;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.tomcat.jni.SSLContext;
public class Reservation extends ActionSupport{
    public static int userId;
    public static int routeId;
    public static int classId;    
    public static ArrayList<ACSeaterSeats> acSeats=new ArrayList<>();
    public static int pnrIdByTable;
    public static ArrayList<SeatsBooked> seats=new ArrayList<>();
    public static float fare=0;

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }
    
    public int getPnrIdByTable() {
        return pnrIdByTable;
    }

    public void setPnrIdByTable(int pnrIdByTable) {
        this.pnrIdByTable = pnrIdByTable;
    }
     public static void writeSeatsBooked(ArrayList<Integer> seatNo,int classId)
    {
        System.out.println("com.reservation.Reservation.writeSeatsBooked()");
        for(int i=0;i<seatNo.size();i++)
        {
            System.out.println(seatNo.get(i));
            String seatType=readClassType(classId);
            SeatsBooked obj=new SeatsBooked(seatNo.get(i), seatType,classId);
            seats.add(obj);
            updateBookedseats(seatNo.get(i), classId);
        }
        System.out.println("seat array in backend");
        
        seats.sort((o1,o2)->o1.getSeatNo()-o2.getSeatNo());
        for(int i=0;i<seats.size();i++)
        {
            System.out.println(seats.get(i).getSeatNo()+"  "+seats.get(i).getSeatType());
        }
    }
   public static int updateBookedseats(int seatNo,int classId)
   {
       int res=0;Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
          System.out.println(seatNo);
          System.out.print(" "+classId);
         String sql = "UPDATE SEAT SET STATUS=true WHERE SEATNO="+seatNo+" AND CLASSID="+classId+";";
         stmt.executeUpdate(sql);
         c.commit();

         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      return res;
   }
    public static ArrayList<Route> readRoute()
   {
       ArrayList<Route> list=new ArrayList<>();
       
       Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM ROUTE;" );
         while ( rs.next() ) {
             int routeId=rs.getInt("routeid");
            String source=rs.getString("source");
            String destination=rs.getString("destination");
            String departTime=rs.getString("departtime");
            String arrivalTime=rs.getString("arrivaltime");
            Route obj=new Route(routeId,source, destination, departTime, arrivalTime);
            list.add(obj);
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      return list;
   
   }
    public static ArrayList<PassengerTicket> readPassengerTicketTable(int userId)
   {
       ArrayList<PassengerTicket> list=new ArrayList<PassengerTicket>();
       Connection c = null;
        Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM PASSENGERTICKET WHERE USERID="+userId+";" );
         int pnrId,routeId,classId;
         float fare;
         while ( rs.next() ) {
             pnrId=rs.getInt("pnrid");
             routeId=rs.getInt("routeid");
             fare=rs.getFloat("fare");
             PassengerTicket obj=new PassengerTicket(pnrId, routeId, userId, fare);
             list.add(obj);
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       return list;
   }
   public static String readSourceByRouteId(int routeId)
   {
       String source = null;
        Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT SOURCE FROM ROUTE WHERE ROUTEID="+routeId+";");
         while ( rs.next() ) {
             source=rs.getString("source");
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       return source;
   }
   public static String readDestinationByRouteId(int routeId)
   {
       String destination = null;
        Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT DESTINATION FROM ROUTE WHERE ROUTEID="+routeId+";");
         while ( rs.next() ) {
             destination=rs.getString("destination");
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       return destination;
   }
   public static ArrayList<PassengerTicketDetails> readPNRDetailsByUserId(int userId)
    {
        System.out.println("com.reservation.Reservation.viewdetails()");
        System.out.println(userId);
        ArrayList<PassengerTicket> passengerTicket=readPassengerTicketTable(userId);
        ArrayList<PassengerTicketDetails> passengerTicketDetails = new ArrayList<>();
        for(int i=0;i<passengerTicket.size();i++)
        {
            String source=readSourceByRouteId(passengerTicket.get(i).getRouteId());
            String destination=readDestinationByRouteId(passengerTicket.get(i).getRouteId());
            PassengerTicketDetails obj=new PassengerTicketDetails(passengerTicket.get(i).getPnrId(), source, destination, passengerTicket.get(i).getFare());
            passengerTicketDetails.add(obj);
        }
        for(int i=0;i<passengerTicketDetails.size();i++)
        {
            System.out.println(passengerTicketDetails.get(i).getPnrId());
        }
        return passengerTicketDetails;
    }
   public static ArrayList<Boolean> readSeatsAvailablity(int classId)
   {
       ArrayList<Boolean> list=new ArrayList<Boolean>();
      Connection c = null;
      Statement stmt = null;
      
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM SEAT WHERE CLASSID="+classId+" ORDER BY SEATID ASC;" );
         while(rs.next())
         {
             int seatid=rs.getInt("seatid");
             boolean flag=rs.getBoolean("status");
             System.out.println(flag+" "+seatid);
             list.add(flag);
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       
       return list;
   }
   public static ArrayList<ClassInfo> readClassInfo(int routeId)
   {
       ArrayList<ClassInfo> list=new ArrayList<>();       
       Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM CLASS_INFO WHERE BUSID=(SELECT BUSID FROM ROUTE WHERE ROUTEID="+routeId+");" );
         while ( rs.next() ) {
             int classId=rs.getInt("classid");
            String seatType=rs.getString("seattype");
            float fare=rs.getFloat("fare");
            ClassInfo obj=new ClassInfo(classId, seatType, fare);
            list.add(obj);
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      return list;
   }
   public static String readBusname(int busId)
   {
       String busName=null;
       Connection c = null;
      Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM BUS_INFO WHERE BUSID="+busId+";" );
         int currId=0;
         String  currPassword=null;
         while ( rs.next() ) {
             busName=rs.getString("busname");
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       return busName;
   }
   public static String readClassType(int classId)
   {
       String classType=null;
       Connection c = null;
        Statement stmt = null;
       ResultSet es;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
             es= stmt.executeQuery( "SELECT * FROM CLASS_INFO WHERE CLASSID="+classId+";" );
             if(es.next())
             {
                 classType=es.getString("seattype");
             }
         es.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       return classType;
   }
   public static ArrayList<Passenger> readPassengerTable(int pnrId)
   {
       ArrayList<Passenger> list=new ArrayList<Passenger>();
       Connection c = null;
        Statement stmt = null;
        ResultSet es = null,rs;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         
         rs = stmt.executeQuery( "SELECT * FROM PASSENGER WHERE PNRID="+pnrId+";" );
         int seatNo = 0,age,seatId,classId = 0;
         
         String passName,gender,classType = null;
         while ( rs.next() ) {
             passName=rs.getString("passname");
             age=rs.getInt("age");
             gender=rs.getString("gender");
             seatNo=rs.getInt("seatno");
             classType=readClassType(classId);
             Passenger obj=new Passenger(seatNo, classType, passName, age, gender,0);
             list.add(obj);
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       
       return list;
               
   }
   public static float readFareByPnrId(int pnrId)
    {
        float fare=0;
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         stmt = c.createStatement();
        String sql="SELECT * FROM PASSENGERTICKET WHERE PNRID="+pnrId+";";
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next())
            fare=rs.getFloat("fare");
        rs.close();
        stmt.close();
        c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
        
        return fare;
    }
   
   public static int readrouteIdByPnr(int pnrId)
   {
       int routeId = 0;
       Connection c = null;
        Statement stmt = null;
        ResultSet es;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         es= stmt.executeQuery( "SELECT * FROM PASSENGERTICKET WHERE PNRID="+pnrId+";" );
             if(es.next())
             {
                 routeId=es.getInt("routeid");
             }
         es.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
    
       return routeId;
   }
    public static ArrayList<Route> readRouteByRouteId(int routeId)
   {
       ArrayList<Route> list=new ArrayList<>();
       Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM ROUTE WHERE routeid="+routeId+";" );
         while ( rs.next() ) {
             String source=rs.getString("source");
            String destination=rs.getString("destination");
            String departTime=rs.getString("departtime");
            String arrivalTime=rs.getString("arrivaltime");
            Route obj=new Route(routeId,source, destination, departTime, arrivalTime);
            list.add(obj);
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      return list;
   
   }
    
    public static ArrayList<SeatsBooked> readSeatsBooked()
    {
        return seats;
    }
    public static float readFare(int classId)
    {
        float fare=0;
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         stmt = c.createStatement();
        String sql="SELECT * FROM CLASS_INFO WHERE CLASSID="+classId+";";
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next())
            fare=rs.getFloat("fare");
        rs.close();
        stmt.close();
        c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
        
        return fare;
    }
    public static int insertPassengerTicketTable(int routeId,int userId,float fare)
    {
        int res=0;
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
           c.setAutoCommit(false);
           stmt = c.createStatement();
           String sql = "INSERT INTO PASSENGERTICKET(routeid,userid,fare) "
              + "VALUES ("+routeId+","+userId+","+fare+");";
           res=1;
           stmt.executeUpdate(sql);
           stmt.close();
           c.commit();
           c.close();
        } catch (Exception e) {
            res=0;
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           System.exit(0);
        }
        System.out.println("Records created successfully");
        return res;
   }
     public static int insertPassengerTable(String passName,int age,String gender,int seatno,String classType)
    {
        int res=0;
        Connection c = null;
        Statement stmt = null;
        int pnrId=-1,busId=-1,seatId=-1;
        System.out.println(routeId+userId+passName+age+gender);
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
           c.setAutoCommit(false);
           stmt = c.createStatement();
           String sql="SELECT * FROM PASSENGERTICKET ORDER BY PNRID DESC LIMIT 1;";
           ResultSet rs=stmt.executeQuery(sql);
           if(rs.next())
               pnrId=rs.getInt("pnrid");
            System.out.println(pnrId);
           sql = "INSERT INTO PASSENGER(pnrid,passname,age,gender,seatno,classtype) "
              + "VALUES ("+pnrId+",'"+passName+"',"+age+",'"+gender+"' ,"+seatno+",'"+classType+"');";
           res=1;
           stmt.executeUpdate(sql);
           stmt.close();
           c.commit();
           c.close();
        } catch (Exception e) {
            res=0;
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           System.exit(0);
        }
        System.out.println("Records created successfully");
        return res;
   }
     public static ArrayList<Passenger> readPassengerTable()
   {
       int pnrId=0;
       ArrayList<Passenger> list=new ArrayList<Passenger>();
       Connection c = null;
        Statement stmt = null;
        ResultSet es = null,rs;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         String sql="SELECT * FROM PASSENGERTICKET ORDER BY PNRID DESC LIMIT 1;";
         rs=stmt.executeQuery(sql);
        if(rs.next())
            pnrId=rs.getInt("pnrid");
         rs = stmt.executeQuery( "SELECT * FROM PASSENGER WHERE PNRID="+pnrId+";" );
         int seatNo = 0,age,seatId,classId = 0;
         String passName,gender,classType = null;
         while ( rs.next() ) {
             passName=rs.getString("passname");
             age=rs.getInt("age");
             gender=rs.getString("gender");
             seatNo=rs.getInt("seatno");
             classType=rs.getString("classtype");
             Passenger obj=new Passenger(seatNo, classType, passName, age, gender,0);
            list.add(obj);
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       
       return list;
               
   }
      public static void flushSeats()
    {
        seats=new ArrayList<SeatsBooked>();
    }
      public static void main(String[] args) {
        
    }
}
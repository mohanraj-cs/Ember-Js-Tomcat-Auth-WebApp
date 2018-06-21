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
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.tomcat.jni.SSLContext;
class TicketInfo
{
	private String depatureTime;
	private String arrivalTime;
	private String fromStaion;
	private String destination;
	private String busName;
	private String mobileNo;
        private float fare;
        
	public String getDepatureTime() {
		return depatureTime;
	}
	public void setDepatureTime(String depatureTime) {
		this.depatureTime = depatureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getFromStaion() {
		return fromStaion;
	}
	public void setFromStaion(String fromStaion) {
		this.fromStaion = fromStaion;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
               
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
        
	public float getFare() {
		return this.fare;
	}
	public void setFare(float fare) {
		this.fare = fare;
                
	}
	public TicketInfo( String depatureTime, String arrivalTime, String fromStaion, String destination,
			String busName, String mobileNo,float fare) {
		super();
		
		this.depatureTime = depatureTime;
		this.arrivalTime = arrivalTime;
		this.fromStaion = fromStaion;
		this.destination = destination;
		this.busName = busName;
		this.mobileNo = mobileNo;
                this.fare=fare;
	}
	
}
public class Reservation extends ActionSupport{
    public static int routeno;
    public static int classno;
    public static int userId;
    public String password;
    public ArrayList<Route> routeList=null;
    public ArrayList<ClassInfo> classTypeList=null;
    public static int routeId;
    public static int classId;
    public ArrayList<Boolean> seatsBookedList=null;
    public String selectedSeats=null;
    public String type1;
    public String type2;
    public String passengerInfo1;
    public String passengerInfo2;
    public static ArrayList<ACSeaterSeats> acSeats=new ArrayList<>();
    public static ArrayList<ACSemiSeaterSeats> acSemiSeats=new ArrayList<>();
    public ArrayList<ACSeaterSeats> ac=new ArrayList<>();
    public ArrayList<ACSemiSeaterSeats> acSemi=new ArrayList<>();
    public ArrayList<Passenger> passengerDetails=new ArrayList<>();
    public ArrayList<PassengerTicketDetails> passengerTicketDetails=new ArrayList<>();
    public ArrayList<PassengerTicket> passengerTicket=new ArrayList<>();
    public static int pnrIdByTable;
    
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
    
    public ArrayList<PassengerTicketDetails> getPassengerTicketDetails() {
        return passengerTicketDetails;
    }

    public void setPassengerTicketDetails(ArrayList<PassengerTicketDetails> passengerTicketDetails) {
        this.passengerTicketDetails = passengerTicketDetails;
    }

    
    public ArrayList<Passenger> getPassengerDetails() {
        return passengerDetails;
    }

    public void setPassengerDetails(ArrayList<Passenger> passengerDetails) {
        this.passengerDetails = passengerDetails;
    }
    
    public String getPassengerInfo2() {
        return passengerInfo2;
    }

    public void setPassengerInfo2(String passengerInfo2) {
        this.passengerInfo2 = passengerInfo2;
    }

    public String getPassengerInfo1() {
        return passengerInfo1;
    }

    public void setPassengerInfo1(String passengerInfo1) {
        this.passengerInfo1 = passengerInfo1;
    }

    
    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }
    
    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }
    public String getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(String selectedSeats) {
        System.out.println("com.reservation.Reservation.setSelectedSeats()");
        this.selectedSeats = selectedSeats;
    }
    public String reBook()
    {
        System.out.println("com.reservation.Reservation.reBook()");
        return "rebook";
    }
    public String insertPassengerInfo()
    {
        
        System.out.println("com.reservation.Reservation.insertPassengerInfo()");
        System.out.println("p1:"+passengerInfo1);
        System.out.println("p2:"+passengerInfo2);
        String[] words1=passengerInfo1.split(" ");
        String[] words2=passengerInfo2.split(" ");
        System.out.println("p1");
        for(int i=0;i<words1.length;i++)
        {
            System.out.println(i+" --> "+words1[i]);
        }
        System.out.println("p2");
        for(int i=0;i<words2.length;i++)
        {
            System.out.println(i+" --> "+words2[i]);
        }
        for(int i=0;i<acSeats.size();i++)
        {
            System.out.println("ac  seatno:  "+acSeats.get(i).getSeatNo());
        }
        for(int i=0;i<acSemiSeats.size();i++)
        {
            System.out.println("ac semi seatno:  "+acSemiSeats.get(i).getSeatNo());
        }
        if(acSeats.size()>0 && words1.length>0)
        {
            for(int i=0,k=0;i<words1.length && k<acSeats.size();i+=3,k++)
           {
               System.out.println(i+"-->  "+words1[i]+words1[i+1]+words1[i+2]);
               String name=words1[i];
               int age=Integer.valueOf(words1[i+1]);
               String gender=words1[i+2];
               System.out.println(name+age+gender);
               insertPassengerTable( name, age, gender, acSeats.get(k).getSeatNo(),acSeats.get(k).getSeatType());
           }
        }
        if(acSemiSeats.size()>0 && words2.length>0)
        {
            for(int i=0,k=0;i<words2.length && k<acSemiSeats.size();i+=3,k++)
           {
               System.out.println(i+"-->  "+words2[i]+words2[i+1]+words2[i+2]);
               String name=words2[i];
               int age=Integer.valueOf(words2[i+1]);
               String gender=words2[i+2];
               System.out.println(name+age+gender);
               insertPassengerTable(name, age, gender, acSemiSeats.get(k).getSeatNo(),acSemiSeats.get(k).getSeatType());
           }
        }
        
        passengerInfo1=null;
        passengerInfo2=null;
        acSeats.clear();
        acSemiSeats.clear();
        readPassengerInformation();
        return "print";
    }
    public String logout()
    {
        return "logout";
    }
    public String readPasssengerInformationByPnrId()
    {
        System.out.println("com.reservation.Reservation.readPassengerInformation()");
        System.out.println(pnrIdByTable);
        passengerDetails=readPassengerTable(pnrIdByTable);
        for(int i=0;i<passengerDetails.size();i++)
        {
            System.out.println(passengerDetails.get(i).getPassName());
        }
        fare=readFareByPnrId(pnrIdByTable);
        return "print";
    }
    public String MainMenu()
    {
        return "mainmenu";
    }
    public String readPassengerInformation(){
        
        System.out.println("com.reservation.Reservation.readPassengerInformation()");
        passengerDetails=readPassengerTable();
        for(int i=0;i<passengerDetails.size();i++)
        {
            System.out.println(passengerDetails.get(i).getPassName());
        }
        return "print";
    }
    public String insertPassengerTicket()
    {
        float fare=0,total=0;
        System.out.println("*********************************************");
        for(int i=0;i<acSeats.size();i++)
        {
            System.out.println("ac seat no"+i+acSeats.get(i).getSeatNo()+" classid: "+acSeats.get(i).getClassId());
        }
        for(int i=0;i<acSemiSeats.size();i++)
        {
            System.out.println("ac semi seat no"+i+acSemiSeats.get(i).getSeatNo()+" classId "+acSemiSeats.get(i).getClassId());
        }
        
        for(int i=0;i<ac.size();i++)
        {
            System.out.println("@ac  no"+i+ac.get(i).getSeatNo()+" classid: "+ac.get(i).getClassId());
        }
        for(int i=0;i<acSemi.size();i++)
        {
            System.out.println("@ac semi no"+i+acSemi.get(i).getSeatNo()+" classId "+acSemi.get(i).getClassId());
        }
        System.out.println(acSeats.size());
        System.out.println(acSemiSeats.size());
        System.out.println(fare+" total "+total);
        if(acSeats.size()>0)
        {
        fare=readFare(acSeats.get(0).getClassId());
        total=fare;
        System.out.println(fare+" total "+total);
        for(int i=1;i<acSeats.size();i++)
        {
            System.out.println(fare+" total "+total);
            total+=fare;
        }
        System.out.println(fare+" total "+total);
        ac.clear();
        ac.addAll(acSeats);
        
        }
        if(acSemiSeats.size()>0)
        {
            System.out.println("com.reservation.Reservation.insertPassengerTicket()");
            System.out.println(acSemiSeats.get(0).getClassId());
            fare=readFare(acSemiSeats.get(0).getClassId());
            System.out.println(fare+" total "+total);
            for(int i=0;i<acSemiSeats.size();i++)
            {
                System.out.println(fare+" total "+total);
                total+=fare;
            }
            acSemi.clear();
            acSemi.addAll(acSemiSeats);
        }
        System.out.println(fare+" total "+total);
        System.out.println("com.reservation.Reservation.insertPassengerTicket()");
        System.out.println(fare);
        insertPassengerTicketTable(routeId, userId, total);
        this.fare=total;
        return "passengerInfo";
    }
    public String addMore()
    {
        System.out.println("com.reservation.Reservation.addMore()");
        String classType=readClassType(classId);
        System.out.println(classId);
        System.out.println(classType);
        if(classType.equals("A/C Seater"))
        {
            System.out.println(type1);
            String[] word=type1.split(" ");
            for(int i=0;i<word.length;i++)
            {
                int seatNo=Integer.valueOf(word[i]);
                ACSeaterSeats obj=new ACSeaterSeats(seatNo, classId);
                acSeats.add(obj);
            }
            for(int i=0;i<acSeats.size();i++)
            {
                updateBookedseats(acSeats.get(i).getSeatNo(), classId);
                System.out.println(acSeats.get(i).getSeatNo());
            }
            return "addmore";
        }
        else if(classType.equals("A/C Semi-Seater"))
        {
            System.out.println("com.reservation.Reservation.addMore()");
        
            System.out.println(type2);
            String[] word=type2.split(" ");
            for(int i=0;i<word.length;i++)
            {
                int seatNo=Integer.valueOf(word[i]);
                ACSemiSeaterSeats obj=new ACSemiSeaterSeats(seatNo, classId);
                acSemiSeats.add(obj);
            }
            for(int i=0;i<acSemiSeats.size();i++)
            {
                updateBookedseats(acSemiSeats.get(i).getSeatNo(), classId);
                System.out.println(acSemiSeats.get(i).getSeatNo());
            }
            return "addmore";
        }
        
        return "error";
    }
    
    public String showAvailableSeats()
    {
        System.out.println("com.reservation.Reservation.showAvailableSeats()");
        System.out.println("classssssssss IIIIIDD :"+classId);
        seatsBookedList=readSeatsAvailablity(classId);
        classno=classId;
        for(int i=0;i<seatsBookedList.size();i++)
        {
            System.out.println("com.reserva"+seatsBookedList.get(i));
        }
        String classType=readClassType(classId);
        System.out.println("classssssssss Type :"+classType);
        if(classType.equals("A/C Seater"))
        {
            return "A/C Seater";
        }
        else if(classType.equals("A/C Semi-Seater"))
        {
            return "A/C Semi-Seater";
        }
        return ERROR;
    }
    public int getClassId() {
        return classId;
    }
    public void setClassId(int classId) {
        System.out.println("com.reservation.Reservation.setClassId()");
        this.classId = classId;
        
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static int printUser(User obj)
    {
        int res=1;
        System.out.println(obj.getUserId());
        System.out.println(obj.getPassword());
        return res;
    }
    public String displayRoute()
    {
        System.out.println("com.reservation.Reservation.displayRoute()");
        routeList=readRoute();
        if(routeList.size()>0)
        {
            return SUCCESS;
        }
        return ERROR;
    }
    public String displayClassType()
    {
        ac.clear();
        acSemi.clear();
        System.out.println("com.reservation.Reservation.displayClassType()");
        System.out.println(routeId);
        classTypeList=readClassInfo(routeId);
        if(classTypeList.size()>0)
        {
            for(int i=0;i<classTypeList.size();i++)
            {
                System.out.println(classTypeList.get(i).getClassType()+classTypeList.get(i).getFare());
            }
            return SUCCESS;
        }
        return ERROR;
    }
    public static void dropTable(String tableName)
    {
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb","postgres", "123");        
         stmt = c.createStatement();
        String sql="DROP TABLE "+tableName+";";
          System.out.println("Reservation.dropTable() dropped succesfully");
        stmt.executeQuery(sql);
        stmt.close();
        c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
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
    
    public static int readNoOfSeats(int classId)
    {
        int noOfSeats=0;
        Connection c = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");        
         stmt = c.createStatement();
        String sql="SELECT * FROM CLASS_INFO WHERE CLASSiD="+classId+";";
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next())
            noOfSeats=rs.getInt("noofseats");
        rs.close();
        stmt.close();
        c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
        return noOfSeats;
    }
    public static ArrayList<ACSeaterSeats> acSeaterSeats=new ArrayList<>();
    public static ArrayList<ACSemiSeaterSeats> acSemiSeaterSeats=new ArrayList<>();
    public static void writeACSeaterSeatsBooked(ArrayList<ACSeaterSeats> obj)
    {
         acSeaterSeats=new ArrayList<>(obj);
    }
    public static void writeACSemiSeaterSeats(ArrayList<ACSemiSeaterSeats> obj)
    {
        acSemiSeaterSeats=new ArrayList<>(obj);
    }
    public static ArrayList<ACSeaterSeats> readACSeaterSeats()
    {
        return acSeaterSeats;
    }
    public static ArrayList<ACSemiSeaterSeats> readACSemiSeaterSeats()
    {
        return acSemiSeaterSeats;
    }
   
    
    public static void createTables()
    {
        Connection c = null;
    Statement stmt = null;
    String sql=null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
//         sql = "CREATE TABLE USER_INFO " +
//            "(USERID INT PRIMARY KEY     NOT NULL," +
//            " PASSWORD    TEXT    NOT NULL, " +
//            " FULLNAME           TEXT     NOT NULL, " +
//            " GENDER    TEXT  NOT NULL," +
//            " AGE INT    NOT NULL,"+
//            "MOBILENO TEXT NOT NULL,"+
//            "CITY TEXT NOT NULL,"+
//            "STATE TEXT NOT NULL)" ;
//        stmt.executeUpdate(sql);
//         sql = "CREATE TABLE BUS_INFO " +
//            "(BUSID INT PRIMARY KEY     NOT NULL," +
//            " BUSNAME    TEXT    NOT NULL) ";
//        stmt.executeUpdate(sql);
//        sql = "CREATE TABLE ROUTE " +
//            "(ROUTEID INT PRIMARY KEY     NOT NULL," +
//            " BUSID INT," +
//            " SOURCE   TEXT     NOT NULL, " +
//            " DESTINATION   TEXT  NOT NULL," +
//            " DEPARTTIME TEXT    NOT NULL,"+
//            " ARRIVALTIME TEXT NOT NULL,"+
//            " CONSTRAINT BUSID FOREIGN KEY (BUSID) " +
//            " REFERENCES BUS_INFO(BUSID))";
//        stmt.executeUpdate(sql);
//         sql = "CREATE TABLE CLASS_INFO " +
//            "(CLASSID INT PRIMARY KEY     NOT NULL," +
//            " SEATTYPE    TEXT    NOT NULL, " +
//            " FARE      FLOAT  NOT NULL) ";
//        stmt.executeUpdate(sql);
//        sql = "CREATE TABLE SEAT " +
//            "(SEATID INT PRIMARY KEY     NOT NULL," +
//            " SEATNO    INT  NOT NULL, " +
//            " CLASSID   INT   , " +
//            " BUSID    INT    ,"+
//            " CONSTRAINT CLASSID FOREIGN KEY (CLASSID) " +
//            " REFERENCES CLASS_INFO(CLASSID),"+
//            " CONSTRAINT BUSID FOREIGN KEY (BUSID) " +
//            " REFERENCES BUS_INFO(BUSID))";
//        stmt.executeUpdate(sql);
        sql = "CREATE TABLE PASSENGERTICKET " +
            "(PNRID SERIAL PRIMARY KEY ," +
            " ROUTEID   INT   , " +
            " CLASSID   INT   , " +
            " USERID    INT   , "+
            " FARE     FLOAT   , "+
            " CONSTRAINT ROUTEID FOREIGN KEY (ROUTEID) " +
            " REFERENCES ROUTE(ROUTEID),"+
            " CONSTRAINT CLASSID FOREIGN KEY (CLASSID) " +
            " REFERENCES CLASS_INFO(CLASSID),"+
            " CONSTRAINT USERID FOREIGN KEY (USERID) " +
            " REFERENCES USER_INFO(USERID))";
        stmt.executeUpdate(sql);
          sql = "CREATE TABLE PASSENGER " +
            "(PASSENGERID  SERIAL PRIMARY KEY ," +
            " SEATID   INT   , " +
            " PNRID   INT   , " +
            " PASSNAME   TEXT   , " +
            " AGE    INT   , "+
            " GENDER     TEXT   , "+
            " CONSTRAINT SEATID FOREIGN KEY (SEATID) " +
            " REFERENCES SEAT(SEATID),"+
            " CONSTRAINT PNRID FOREIGN KEY (PNRID) " +
            " REFERENCES PASSENGERTICKET(PNRID))";
        stmt.executeUpdate(sql);
        
        stmt.close();
        c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
    
    }
    public String validateUser()
    {
        int res=userValidation(userId,password);
        if(res==1)
        {
            System.out.println("com.reservation.Reservation.validateUser()"); 
            return SUCCESS;
        }
        return ERROR;
    }
    public static int insertUserInfoTable(int userId,String password,String fullname,String gender,int age,String mobile,String city,String state)
    {
        int res=0;
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
           c.setAutoCommit(false);
           stmt = c.createStatement();
//           (USERID,PASSWORD,FULLNAME,GENDER,AGE,MOBILENO,CITY,STATE)
           String sql = "INSERT INTO USER_INFO "
              + "VALUES ("+userId+",'"+password+"','"+fullname+"','"+gender+"',"+age+",'"+mobile+"','"+city+"','"+state+"' );";
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
    public static int insertBusInfoTable(int busId,String busName)
    {
        int res=0;
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
           c.setAutoCommit(false);
           stmt = c.createStatement();
           String sql = "INSERT INTO BUS_INFO "
              + "VALUES ("+busId+",'"+busName+"');";
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
   public static int insertRouteTable(int routeId,int busId,String source,String destination,String departTime,String arrivalTime)
    {
        int res=0;
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
           c.setAutoCommit(false);
           stmt = c.createStatement();
           String sql = "INSERT INTO ROUTE "
              + "VALUES ("+routeId+","+busId+",'"+source+"','"+destination+"','"+departTime+"','"+arrivalTime+"' );";
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
   public static int insertClassInfoTable(int classId,String seatType,Float fare,int noOfseats,int busId)
    {
        int res=0;
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
           c.setAutoCommit(false);
           stmt = c.createStatement();
           String sql = "INSERT INTO CLASS_INFO "
              + "VALUES ("+classId+",'"+seatType+"',"+fare+","+noOfseats+","+busId+");";
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
   public static int insertSeatTable(int seatId,int seatNo,int class_id,int bus_id,boolean status)
    {
        int res=0;
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
           c.setAutoCommit(false);
           stmt = c.createStatement();
           String sql = "INSERT INTO SEAT "
              + "VALUES ("+seatId+","+seatNo+","+class_id+","+bus_id+","+status+");";
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
   
  
   public static int readSeatNo(int seatId)
   {
       int seatNo=0;
       Connection c = null;
        Statement stmt = null;
       ResultSet es;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         
             es= stmt.executeQuery( "SELECT * FROM SEAT WHERE SEATID="+seatId+";" );
             
             if(es.next())
             {
                 seatNo=es.getInt("seatno");
             }
         
         es.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       
       return seatNo;
   }
   
   
   public static int readClassIdByRouteIdSeatType(int routeId,String seatType)
   {
       int classId=0;
        Connection c = null;
        Statement stmt = null;
        ResultSet es;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         es= stmt.executeQuery( "SELECT * FROM CLASS_INFO WHERE BUSID=(SELECT BUSID FROM ROUTE WHERE ROUTEID="+routeId+") AND SEATTYPE='"+seatType+"';" );
             if(es.next())
             {
                 classId=es.getInt("classid");
             }
         es.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
    
       return classId;
   }
   public static int readClassId(int seatId)
   {
       int classId=0;
       Connection c = null;
        Statement stmt = null;
       ResultSet es;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
             es= stmt.executeQuery( "SELECT * FROM SEAT WHERE SEATID="+seatId+";" );
             if(es.next())
             {
                 classId=es.getInt("classid");
             }
         es.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       return classId;
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
      

   public static ArrayList<String> readBusClassType()
   {
       
       Connection c = null;
      Statement stmt = null;
       ArrayList<String> list=new ArrayList<String>();
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM CLASS_INFO;" );
         int currId=0;
         String  currPassword=null;
         while ( rs.next() ) {
             list.add(rs.getString("seattype"));
            
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
   public static ArrayList<TicketInfo> readTicketDetails(float fare,int userId,int routeId)
   {
       int pnr=0;
       ArrayList<TicketInfo> list=new ArrayList<TicketInfo>();
	int busId=0;
	String depatureTime = null;
	String arrivalTime = null;
	String source = null;
	String destination = null;
	String busName = null;
	String mobileNo = null;
        
        Connection c = null;
        
      Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM ROUTE WHERE ROUTEID="+routeId+";" );
         String  currPassword=null;
         if ( rs.next() ) {
            busId=rs.getInt("busid");
            depatureTime=rs.getString("departtime");
            arrivalTime=rs.getString("arrivaltime");
            source=rs.getString("source");
            destination=rs.getString("destination");
        }
           System.out.println(busId+depatureTime+arrivalTime+source+destination);
         rs = stmt.executeQuery( "SELECT * FROM USER_INFO WHERE USERID="+userId+";" );
         if ( rs.next() ) {
             mobileNo=rs.getString("mobileno");
        }
         
           System.out.println(mobileNo);
         rs = stmt.executeQuery( "SELECT * FROM BUS_INFO WHERE BUSID="+busId+";" );
         if ( rs.next() ) {
             busName=rs.getString("busname");
        }
           System.out.println(busName);
         
           System.out.println(fare);
           TicketInfo obj=new TicketInfo( depatureTime, arrivalTime, source, destination, busName, mobileNo,fare);
           list.add(obj);
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
       return list;
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
   public static float bookSeats(int classId,ArrayList<Integer> seatsBooked)
   {
       
       System.out.println(classId);
       for(int i=0;i<seatsBooked.size();i++)
       {
           System.out.println(seatsBooked.get(i));
       }
       int res=0;
       Connection c = null;
      Statement stmt = null;
      ResultSet rs = null;
      float fare=0,ammount=0;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/RedBus","postgres", "123");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         for(int i=0;i<seatsBooked.size();i++)
         { 
            String sql="SELECT * FROM CLASS_INFO WHERE CLASSID="+classId+";";
            rs = stmt.executeQuery( sql );
            if(rs.next())
             ammount=rs.getFloat("fare");
            fare+=ammount;
             System.out.println(ammount);
             updateBookedseats(seatsBooked.get(i)-1,classId);
                     }
           System.out.println(fare);
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      
       //insertPassengerTicketTable(0, routeId, classId, 0, fare);
       
       return fare;
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
   public static int transaction(int routeId,int classId,int userId,float fare)
   {
       int res=0;
       insertPassengerTicketTable(routeId, userId, fare);
       return res;
   }
   public static int addNewUserpublic(int userId,String password,String fullname,String gender,int age,String mobile,String city,String state)
   {
       int res=0;
       res=insertUserInfoTable(userId,password,fullname,gender,age,mobile,city,state);
       return res;
   }
   public static void main(String args[]) {
    Connection c = null;
    Statement stmt = null;
    String sql=null;
//       insertPassengerTable(0, 0, 0, "mohan",22, "m");
//    
    
    
       //insertUserInfoTable(0, "1234", "mohan", "male",22,"9840549517","chennai","tamilnadu");
       //validateUser(0, "1234");
//       insertBusInfoTable(0,"Volvo Travels");
//       insertBusInfoTable(1,"Parveen Travels");
//       insertBusInfoTable(2,"Pallavan Travels");
//
//        insertClassInfoTable(0, "A/C Seater", 800f,10,0);
//        insertClassInfoTable(1, "A/C Semi-Seater",600f,20,0);
//        
//        insertClassInfoTable(2, "A/C Seater", 740f,10,1);
//        insertClassInfoTable(3, "A/C Semi-Seater",540f,20,1);
//        
//        insertClassInfoTable(4, "A/C Seater", 640f,10,2);
//        insertClassInfoTable(5, "A/C Semi-Seater",440f,20,2);

//        insertRouteTable(0, 0,"koyambedu", "Villupuram", "9:00A.M", "10:00A.M");
//        insertRouteTable(1, 1,"Chennai", "Bangalore", "4:00P.M", "5:00P.M");
//        insertRouteTable(2, 2,"Thiruvanmiyur", "Velankani", "7:00P.M", "8:00P.M");
    
    //insitializing seats
//        int i=1,k=1,j=1;
//        for(i=1;i<=10;i++,k++)
//        insertSeatTable(k, i, 0, 0, false);
//        j=i;
//        for(i=1;i<=20;i++,k++,j++)
//        insertSeatTable(k, j, 1, 0, false);
//        
//        for(i=1;i<=10;i++,k++)
//        insertSeatTable(k, i, 2, 1, false);
//        j=i;
//        for(i=1;i<=20;i++,k++,j++)
//        insertSeatTable(k, j, 3, 1, false);
//        
//        for(i=1;i<=10;i++,k++)
//        insertSeatTable(k, i, 4, 2, false);
//        j=i;
//        for(i=1;i<=20;i++,k++,j++)
//        insertSeatTable(k, j, 5, 2, false);
        

        //insertSeatTable(0, 0, 0, 0);
       //insertPassengerTicketTable(0, 0, 0, 2, 0);
        
//       createTables();
            
}

  
}
<%-- 
    Document   : index
    Created on : 20 Jun, 2018, 6:21:56 PM
    Author     : Administrator
--%>

<%@page import="java.security.Principal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<style>
		 div
		  {
			text-align:right;
		  }
		</style>
    </head>
    <body>
		
        <center>
		<div>
		<a class="btn btn-danger" href="logout.jsp">Log Out</a>
		</div>
		</center>
        <% 
              Date createTime = new Date(session.getCreationTime());
            // Get last access time of this Webpage.
            Date lastAccessTime = new Date(session.getLastAccessedTime());

            String title = "Welcome Back to my website";

            // Check if this is new comer on your Webpage.
            if (session.isNew() ){
               title = "Welcome to my website";
            } 
             final Principal userPrincipal = request.getUserPrincipal();
             String userName=userPrincipal.getName();
			session.setAttribute("userName", userName);
            
        %>
		
      <center>
         <h4>Session Tracking	<%out.print(title);%></h4>
      </center>
      <h5>
      <table border = "1" align = "center"> 
         <tr bgcolor = "#949494">
            <th>Session info</th>
            <th>Value</th>
         </tr> 
         <tr>
            <td>id</td>
            <td><% out.print( session.getId()); %></td>
         </tr> 
         <tr>
            <td>Creation Time</td>
            <td><% out.print(createTime); %></td>
         </tr> 
         <tr>
            <td>Time of Last Access</td>
            <td><% out.print(lastAccessTime); %></td>
         </tr> 
         <tr>
            <td>User ID</td>
            <td><% out.print(userName); %></td>
         </tr> 
      </table> 
	  </h5>
	<%@include file="index.html" %>
    </body>
</html>

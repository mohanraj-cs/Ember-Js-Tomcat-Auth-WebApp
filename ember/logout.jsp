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
		.loader {
		  border: 16px solid #f3f3f3;
		  border-radius: 50%;
		  border-top: 16px solid #3498db;
		  width: 120px;
		  height: 120px;
		  -webkit-animation: spin 2s linear infinite; /* Safari */
		  animation: spin 2s linear infinite;
		}

		/* Safari */
		@-webkit-keyframes spin {
		  0% { -webkit-transform: rotate(0deg); }
		  100% { -webkit-transform: rotate(360deg); }
		}

		@keyframes spin {
		  0% { transform: rotate(0deg); }
		  100% { transform: rotate(360deg); }
		}
		</style>
		<script>window.onload = function() {
			setTimeout(function(){out.print("asd");},1000);
			setTimeout(function(){window.location.href="/ember"},3000);
		};

		</script>
    </head>
    <body>
        <% 
			session.invalidate();

        %>
		
      
	<center>
		
		<div class="page-header">
		<h1>Logging Out.....</h1>
		</div>
		<div class="loader"></div>
		<center>
		
    </body>
</html>

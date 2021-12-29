<%@ page import="model.weatherbean"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Weather</title>
</head>
<body>

	<header><%@ include file="Header.jsp"%></header>
	<%
		@SuppressWarnings("unchecked") 
		ArrayList<weatherbean> cb = (ArrayList<weatherbean>) request.getAttribute("cbean"); 
	%>

	<div class="search-location">
		<%@ include file="WEB-INF/weatherForm.html"%>


		<div class="text-class">
			<%
			weatherbean wb = (weatherbean) request.getAttribute("wbean");
			if (wb!=null){
				out.print(wb.getCityStr().toUpperCase() + " " + wb.getTemperature() + "°C<br>");
				out.print(wb.getClouds() + "% cloudiness<br>");
				out.print(wb.getDateTime());
			}
			%>
		</div>
	</div>


	<div class="last-locations">
		<%

		
			out.print("<ul style=\"color:black\">"); 
			try {
				for(int i = cb.size()-1; i >= 0; i--) {
					out.print("<li>" + cb.get(i) + "</li>");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			out.print("</ul>");
		%>
	</div>

	

</body>
</html>
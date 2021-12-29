package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.WeatherDataParser;
import model.weatherbean;

/**
 * Servlet implementation class apiservlet
 */
@WebServlet("/OpenWeatherServlet")
public class OpenWeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenWeatherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cityStr = request.getParameter("city");
		String countryStr = request.getParameter("country");
		weatherbean wb = new weatherbean(cityStr, countryStr);

		WeatherDataParser.getWeather(wb);

		//check if cookies are accepted then create a cookie
		if ((request.getCookies() != null)&&
			(Cookies.cookiesAccepted(request.getCookies(), request.getCookies().length)))
			{
				Cookie ck = new Cookie(cityStr, countryStr);// creating cookie object				
				ck.setMaxAge(3600); // set how long the cookie lasts, seconds
				response.addCookie(ck);// adding cookie in the response
			}
		
		
		request.setAttribute("wbean", wb);

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

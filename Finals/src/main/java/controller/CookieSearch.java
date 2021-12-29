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
 * Servlet implementation class CookieSearch
 */
@WebServlet("/CookieSearch")
public class CookieSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<weatherbean> wb = new ArrayList<weatherbean>();
		
		//get all cookies and search with API
		try {
			Cookie ck[] = request.getCookies();
			int cookie_iterator = request.getCookies().length;
			int parser_iterator = 0;

			for (int i = 0; i < cookie_iterator; i++) {
				//System.out.println(ck[i].getName());
				if ((!ck[i].getName().equals("JSESSIONID"))&&(!ck[i].getName().equals("cookiesaccepted"))) {
					wb.add(new weatherbean(ck[i].getName(), ck[i].getValue()));
					WeatherDataParser.getWeather(wb.get(parser_iterator++));
					
				}
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
				
		request.setAttribute("cbean", wb);

		RequestDispatcher rd = request.getRequestDispatcher("userWeatherPage.jsp");
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

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cookieCreator
 */
@WebServlet("/Cookies")
public class Cookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cookies() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

			String n = request.getParameter("acceptcookies");
			Cookie ck = new Cookie("cookiesaccepted", n);
			String nextPage = "";
			
			
			if (n.equals("no")){
				Cookie removeCookies[] = request.getCookies();
				nextPage = "index.jsp";
				for(int i = 0 ; i < request.getCookies().length ; i++){
					removeCookies[i].setMaxAge(0);
					response.addCookie(removeCookies[i]);
				}
			}else {
				ck.setMaxAge(3600); 
				response.addCookie(ck);
				nextPage = "userWeatherPage.jsp";
			}
			
			
			response.sendRedirect(nextPage);
			


		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public static boolean cookiesAccepted(Cookie ck[], int nrCookies) {
		

		//check if acceptcookie is created
		boolean cookiesAccepted = false;
		try {
			if (ck[0].getName().isEmpty()==false){
				for(int i = 0 ; i < nrCookies ; i++){
					if (ck[i].getName().equals("cookiesaccepted"))
						cookiesAccepted = ck[i].getValue().equals("yes");
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return cookiesAccepted;
		
	}

}
package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newPremium")
public class AdminAccountRedirect1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//dla przycisku pierwszego przekieruj na strone admina
		if(request.getParameter("newPremium").equals("dodaj kolejny")) {
			response.sendRedirect("setPremium.jsp");
		}
	}

}

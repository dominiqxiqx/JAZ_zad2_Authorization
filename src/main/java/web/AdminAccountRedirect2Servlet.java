package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginPage")
public class AdminAccountRedirect2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //dla przyciku drugiego przekieruj na strone logowania
		if(request.getParameter("loginPage").equals("strona logowania")) {
		response.sendRedirect("index.jsp");
	}
	}

}

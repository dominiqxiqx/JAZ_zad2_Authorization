package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DummyHsqlUserRepository;
import db.HsqlRepositoryCatalog;
import domain.User;

@WebServlet("/viewAll")
public class ViewAllUsersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("viewAll").equals("pokaż użytkowników")) {
			try{
			//polaczenie z baza danych
			HsqlRepositoryCatalog catalog = new HsqlRepositoryCatalog();
			DummyHsqlUserRepository repository = (DummyHsqlUserRepository) catalog.users();
			repository.initDb();
			
			//pobranie uzytkownikow z bazy danych
			ArrayList<User> db = repository.getAll();
			
			//wyświetlenie wszystkich uzytkownikow
			response.setContentType("text/html; charset=UTF-8");
			for (User u : db) {
				response.getWriter().println("nazwa użytkownika: " + u.getUsername() + ", email: " + u.getEmail() + ", prawa dostępu: " + u.getPrivillege() + "<br /><br />");
			}
			
			} catch (SQLException exc) {
				exc.printStackTrace();
			}
		}
	}

}

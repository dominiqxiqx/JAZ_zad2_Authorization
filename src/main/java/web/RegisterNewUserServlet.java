package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DummyHsqlUserRepository;
import domain.User;
import domain.UserPrivillege;

@WebServlet("/register")
public class RegisterNewUserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private DummyHsqlUserRepository repository;
	private User user;
	
	public void init(ServletConfig config) throws ServletException {
				
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//wczytanie obiektow z sesji
		HttpSession session = request.getSession(false);
		repository = (DummyHsqlUserRepository) session.getAttribute("repository");
		user = (User) session.getAttribute("user");
		
		//wczytanie wpisow z bazy danych
		ArrayList<User> dummyRepo = new ArrayList<User>();
		try{
		dummyRepo = repository.getAll();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		
		//porownanie wpisow z bazy danych z danymi z formularza
		boolean newUser=true;
		for(User u : dummyRepo) {
			if(u.getEmail().equalsIgnoreCase(user.getEmail())) {
				newUser=false;
				break;
			}
		}
			
		//dodanie NOWEGO uzytkownika zwyklego do bazy
	if(newUser) {
		
		try {
			user.setPrivillege(UserPrivillege.plain.toString());
			repository.add(user);
			} catch(SQLException exc) {
				exc.printStackTrace();
			}
			
			response.sendRedirect("success.jsp");
	}
		
		
	}
	
	public User retrieveUserFromRequest(HttpServletRequest request) {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setPrivillege(UserPrivillege.anonymous.toString());
		return user;
	}

}

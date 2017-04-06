package web.filters;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DummyHsqlUserRepository;
import db.HsqlRepositoryCatalog;
import db.RepositoryCatalog;
import domain.User;

@WebFilter("/register")
public class RegisterNewUserFilter implements Filter{
	
	private RepositoryCatalog catalog;
	private DummyHsqlUserRepository repository;
	private User user;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws  IOException, ServletException {
		
		//przygotowanie obiektow: request, response
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//przygotowanie bazy danych
		try{
		catalog = new HsqlRepositoryCatalog();
		repository = (DummyHsqlUserRepository) catalog.users();
		repository.initDb();
		repository.createTableUser();
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		
		//pobranie danych o uzytkowniku z formularza
		user = repository.retrieveUserFromRequest(httpRequest);
		
		//przygotowanie sesji
		HttpSession session = httpRequest.getSession(true);
		session.setAttribute("repository", repository);
		session.setAttribute("user", user);
		
		//sprawdza czy rejestrujacy sie uzytkownik juz istnieje w bazie, gdy istnieje przekieruj na strone profilu
		try{
		checkIfUserIsAnonymous(httpRequest, httpResponse, user, repository);
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	//sprawdz czy rejestrujacy sie uzytkownik juz istnieje w bazie, gdy istnieje przekieruj na strone profilu
	public void checkIfUserIsAnonymous(HttpServletRequest request, HttpServletResponse response, User user, DummyHsqlUserRepository repository) throws SQLException, IOException{
		
		boolean newUser = true;
				
			ArrayList<User> dummyRepo = repository.getAll();
			
			for(User u : dummyRepo) {
				if(u.getEmail().equalsIgnoreCase(user.getEmail())) {
					newUser=false;
					break;
				}
			}
				
		if(!newUser) {
			response.sendRedirect("profile.jsp");
			return;
		}
		
		
	}
}

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

import db.DummyHsqlUserRepository;
import db.HsqlRepositoryCatalog;
import domain.User;
import domain.UserPrivillege;

//mechanizm logowania uzytkownika
@WebFilter("/login")
public class LoginUserFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException  {
		
		//przygotowanie obiektow: request, response
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//pobranie danych logowania z formularza
		String username = httpRequest.getParameter("username");
		String password = httpRequest.getParameter("password");
		
		//przekierowanie uzytkownika admin
		if(username.equals("admin") && password.equals("admin")) {
			httpResponse.sendRedirect("setPremium.jsp");
			return;
		}
		
		//polaczenie z baza danych
		DummyHsqlUserRepository repository = null;
		try {
		HsqlRepositoryCatalog catalog = new HsqlRepositoryCatalog();
		repository = (DummyHsqlUserRepository) catalog.users();
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
		
		//wyszukiwanie logujacego sie uzytkownika  w bazie danych
		ArrayList<User> user = null;
		try{
			repository.initDb();
			user = repository.getAll();
		} catch(SQLException exc) {
			exc.printStackTrace();
		}
		
		//flaga - czy uzytkownik istnieje w bazie danych
		boolean loginUser = false;
		UserPrivillege privillege = UserPrivillege.anonymous;
		for(User u : user) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				loginUser = true;
				//ustal prawa dostepu logujacego sie uzytkownika
				switch(u.getPrivillege()) {
				case "plain": privillege = UserPrivillege.plain;
				break;
				case "premium": privillege = UserPrivillege.premium;
				break;
				case "admin": privillege = UserPrivillege.admin;
				break;
				}
				//przerwij wyszukiwanie - uzytkownik znaleziony
				break;
			}
		}
		
		//przekierowanie uzytkownika na wlasciwa strone w zaleznosci od uprawnien
		if(loginUser) {
			
		
			switch(privillege.toString()) {
				case "anonymous" : httpResponse.sendRedirect("loginAgain.jsp"); break;	
				case "plain" : httpResponse.sendRedirect("profile.jsp"); break;
				case "premium" : httpResponse.sendRedirect("premium.jsp"); break;
				case "admin" : httpResponse.sendRedirect("setPremium.jsp"); break;
			}
		
		}
					
		//w razie prawidlowych danych do zalogowania uzytkownika wyjdz z filtra i nie przekierowuj do servletu
		//w razie nieprawidlowych danych logowania przekieruj do servletu
		if(loginUser) return;
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}

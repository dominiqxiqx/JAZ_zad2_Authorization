package web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import domain.UserPrivillege;

@WebFilter("/register")
public class RegisterNewUserFormValidationFilter implements Filter{
	
	User user;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		
		//przygotowanie obiektow: request, response
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		httpResponse.setContentType("text/html; charset=UTF-8");
		
		//wczytanie danych rejestracyjnych z formularza
		user = retrieveUserFromRequest(httpRequest);
		
		
		//walidacja formularza
		boolean repeatForm = false;
		if(user.getUsername() == null || user.getUsername().equals("")) {
			httpResponse.getWriter().println("Nie podano nazwy użytkownika! <br />");
			repeatForm = true;
		} if (user.getPassword() == null || user.getPassword().equals("")) {
			httpResponse.getWriter().println("Nie podano hasła! <br />");
			repeatForm = true;
		} if (httpRequest.getParameter("passwordRepeat") == null || httpRequest.getParameter("passwordRepeat").equals("")) {
			httpResponse.getWriter().println("Nie podano powtórnie hasła! <br />");
			repeatForm = true;
		}  if (user.getEmail() == null || user.getEmail().equals("")) {
			httpResponse.getWriter().println("Nie podano adresu email! <br />");
			repeatForm = true;
		}
		
		//walidacja haseł formularza
		if (!httpRequest.getParameter("passwordRepeat").equals(user.getPassword())) {
			httpResponse.getWriter().println("Podane hasła nie są takie same! <br />  <br />");
			repeatForm = true;
		}
		
		//powtorz formularz
		if(repeatForm) return;
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
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

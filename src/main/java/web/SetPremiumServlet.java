package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DummyHsqlUserRepository;
import db.HsqlRepositoryCatalog;


@WebServlet("/addPremium")
public class SetPremiumServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("userPremium");
		
		try{
		//inicjalizacja bazy danych
		HsqlRepositoryCatalog catalog = new HsqlRepositoryCatalog();
		DummyHsqlUserRepository repository = (DummyHsqlUserRepository) catalog.users();
		repository.initDb();
		 
		//
		PreparedStatement preparedStmt = null;
		String sql = "UPDATE User SET privillege = ? WHERE username = \'" + username +"\'";
		
		Connection connection = repository.getConnection();
		
		preparedStmt = connection.prepareStatement(sql);
		preparedStmt.setString(1, "premium");
		preparedStmt.executeUpdate();
		
		}catch(SQLException exc) {
			exc.printStackTrace();
		}
		
		//dodanie do sesji nazwy nowego uzytkownika premium
		HttpSession session = request.getSession(false);
		session.setAttribute("userPremium", username);
		response.sendRedirect("afterSettingPremium.jsp");
	}

}

package db;

import java.sql.SQLException;

public interface RepositoryCatalog {
	
	public UserRepository users() throws SQLException;
}

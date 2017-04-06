package db;

import java.sql.SQLException;

public class HsqlRepositoryCatalog implements RepositoryCatalog{
	
	@Override
	public UserRepository users() throws SQLException{
		return new DummyHsqlUserRepository();
	}
}

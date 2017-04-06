package db;

import java.sql.SQLException;

import domain.User;

public interface UserRepository extends Repository<User>{
	
	public User getUser(String name) throws SQLException;
	public String getPassword(String name) throws SQLException;
	public String getEmail(String name) throws SQLException;
	
}

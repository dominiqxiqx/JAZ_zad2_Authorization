package db;

import java.sql.SQLException;
import java.util.List;

public interface Repository <Tentity> {
	public void add(Tentity entity) throws SQLException;
	public List<Tentity> getAll() throws SQLException;
}

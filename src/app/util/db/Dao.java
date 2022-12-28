package app.util.db;

import java.util.List;

public interface Dao<T> {
	
	public List<T> consultAll();
	
	public void save(T ob);
	
	public void update(T ob);
	
	public void delete(T ob);
	
}

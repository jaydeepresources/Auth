package dao;

import model.User;

public interface UserDAO {
	
	boolean login(User user);
	boolean register(User user);

}
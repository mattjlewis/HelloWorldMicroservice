package test.hwms.model;

import java.util.Collection;

public interface IUserService {
	Collection<User> getAllUsers();
	User getUser(int id) throws UserNotFoundException;
	User createUser(String name, String email);
	User updateUser(int id, String name, String email) throws UserNotFoundException;
	User deleteUser(int id) throws UserNotFoundException;
}

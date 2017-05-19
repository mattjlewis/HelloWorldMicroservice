package test.hwms.model;

import java.util.Collection;

public interface IUserService {
	Collection<User> getAllUsers();
	User getUser(String id) throws UserNotFoundException;
	User createUser(String name, String email);
	User updateUser(String id, String name, String email) throws UserNotFoundException;
	User deleteUser(String id) throws UserNotFoundException;
}

package test.hwms.domain;

import java.util.Collection;

public interface IUserService {
	Collection<User> getAllUsers();
	User getUser(int id) throws UserNotFoundException;
	User createUser(String name, String email);
	void updateUser(int id, String name, String email) throws UserNotFoundException;
	void deleteUser(int id) throws UserNotFoundException;
}

package test.hwms.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import test.hwms.model.IUserService;
import test.hwms.model.User;
import test.hwms.model.UserNotFoundException;

public class StaticUserService implements IUserService {
	private static Map<String, User> users;
	private static int lastId;
	
	static {
		users = new HashMap<>();
		String id = Integer.toString(lastId++);
		users.put(id, new User(id, "fred", "fred@gmail.com"));
		id = Integer.toString(lastId++);
		users.put(id, new User(id, "bob", "bob@hotmail.com"));
	}

	@Override
	public Collection<User> getAllUsers() {
		return users.values();
	}

	@Override
	public User getUser(String id) throws UserNotFoundException {
		synchronized (users) {
			User user = users.get(id);
			if (user == null) {
				throw new UserNotFoundException(id);
			}
			return user;
		}
	}

	@Override
	public User createUser(String name, String email) {
		synchronized (users) {
			String id = Integer.toString(lastId++);
			User user = new User(id, name, email);
			users.put(id, user);
			return user;
		}
	}

	@Override
	public User updateUser(String id, String name, String email) throws UserNotFoundException {
		synchronized (users) {
			User user = users.get(id);
			if (user == null) {
				throw new UserNotFoundException(id);
			}
			user.setName(name);
			user.setEmail(email);
			return user;
		}
	}
	
	@Override
	public User deleteUser(String id) throws UserNotFoundException {
		synchronized (users) {
			User user = users.remove(id);
			if (user == null) {
				throw new UserNotFoundException(id);
			}
			
			return user;
		}
	}
}

package test.hwms.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import test.hwms.model.IUserService;
import test.hwms.model.User;
import test.hwms.model.UserNotFoundException;

public class StaticUserService implements IUserService {
	private static Map<Integer, User> users;
	private static int lastId;
	
	static {
		users = new HashMap<>();
		int id = lastId++;
		users.put(Integer.valueOf(id), new User(id, "fred", "fred@gmail.com"));
		id = lastId++;
		users.put(Integer.valueOf(id), new User(id, "bob", "bob@hotmail.com"));
	}

	@Override
	public Collection<User> getAllUsers() {
		return users.values();
	}

	@Override
	public User getUser(int id) throws UserNotFoundException {
		synchronized (users) {
			User user = users.get(Integer.valueOf(id));
			if (user == null) {
				throw new UserNotFoundException(id);
			}
			return user;
		}
	}

	@Override
	public User createUser(String name, String email) {
		synchronized (users) {
			User user = new User(lastId++, name, email);
			users.put(Integer.valueOf(user.getId()), user);
			return user;
		}
	}

	@Override
	public User updateUser(int id, String name, String email) throws UserNotFoundException {
		synchronized (users) {
			User user = users.get(Integer.valueOf(id));
			if (user == null) {
				throw new UserNotFoundException(id);
			}
			user.setName(name);
			user.setEmail(email);
			return user;
		}
	}
	
	@Override
	public User deleteUser(int id) throws UserNotFoundException {
		synchronized (users) {
			User user = users.remove(Integer.valueOf(id));
			if (user == null) {
				throw new UserNotFoundException(id);
			}
			
			return user;
		}
	}
}

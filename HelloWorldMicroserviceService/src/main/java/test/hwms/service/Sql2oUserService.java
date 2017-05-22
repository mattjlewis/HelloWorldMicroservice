package test.hwms.service;

import java.util.Collection;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import test.hwms.model.IUserService;
import test.hwms.model.User;
import test.hwms.model.UserNotFoundException;

public class Sql2oUserService implements IUserService {
	private Sql2o sql2o;
	
	public Sql2oUserService() {
		String db_hostname = "hwmsdb";
		int db_port = 3306;
		String db_username = "hwms_owner";
		String db_password = "helloworld";
		this.sql2o = new Sql2o("jdbc://" + db_hostname + ":" + db_port + "/hwms", db_username, db_password);
	}

	@Override
	public Collection<User> getAllUsers() {
		List<User> users;
		try (Connection conn = sql2o.open()) {
			users = conn.createQuery("SELECT user_id, name, email FROM USERS").executeAndFetch(User.class);
		}
		return users;
	}

	@Override
	public User getUser(int id) throws UserNotFoundException {
		User user;
		try (Connection conn = sql2o.open()) {
			user = conn.createQuery("SELECT user_id, name, email FROM USERS WHERE user_id=:user_id")
					.addParameter("user_id", id)
					.executeAndFetchFirst(User.class);
		}
		return user;
	}

	@Override
	public User createUser(String name, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(int id, String name, String email) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(int id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}

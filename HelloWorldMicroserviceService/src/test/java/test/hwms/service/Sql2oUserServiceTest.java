package test.hwms.service;

import java.util.Collection;

import org.junit.*;

import test.hwms.domain.User;
import test.hwms.domain.UserNotFoundException;

@SuppressWarnings("static-method")
public class Sql2oUserServiceTest {
	private static Sql2oUserService userService;
	/*
	@BeforeClass
	public static void beforeClass() {
		userService = new Sql2oUserService("192.168.99.100", 3306, "hwms_owner", "helloworld");
	}
	
	@AfterClass
	public static void afterClass() {
	}
	
	@Test
	public void getAllUsers() {
		Collection<User> users = userService.getAllUsers();
		Assert.assertNotNull("users", users);
	}
	
	@Test
	public void crudUser() throws UserNotFoundException {
		Collection<User> users = userService.getAllUsers();
		Assert.assertNotNull("users", users);
		int start_size = users.size();
		System.out.println("Starting with " + start_size + " users");

		String name = "Test1";
		String email = "test1@hwms.test";
		User created_user = userService.createUser(name, email);
		Assert.assertNotNull("Created user", created_user);
		Assert.assertEquals("name", name, created_user.getName());
		Assert.assertEquals("email", email, created_user.getEmail());
		
		users = userService.getAllUsers();
		Assert.assertNotNull("users", users);
		Assert.assertEquals("# users", start_size+1, users.size());
		Assert.assertEquals("users[" + start_size + "]", created_user, users.toArray()[start_size]);
		
		User get_user = userService.getUser(created_user.getId());
		Assert.assertNotNull("Get user", get_user);
		Assert.assertEquals("get user", created_user, get_user);
		
		String updated_name = "Test2";
		String updated_email = "test2@hwms.test";
		userService.updateUser(created_user.getId(), updated_name, updated_email);
		
		userService.deleteUser(created_user.getId());
	}
	
	@Test
	public void getUserNotFound() {
		try {
			User user = userService.getUser(999);
			Assert.fail("User was found: " + user);
		} catch (UserNotFoundException e) {
		}
	}
	
	@Test
	public void deleteUserNotFound() {
		try {
			userService.deleteUser(999);
			Assert.fail("Delete user should have failed");
		} catch (UserNotFoundException e) {
		}
	}
	*/
}

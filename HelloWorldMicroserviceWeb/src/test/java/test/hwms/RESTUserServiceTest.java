package test.hwms;

import java.util.Collection;

import org.junit.*;

import spark.Spark;
import test.hwms.domain.IUserService;
import test.hwms.domain.User;
import test.hwms.domain.UserNotFoundException;
import test.hwms.service.Main;
import test.hwms.service.StaticUserService;
import test.hwms.util.PropertyUtil;
import test.hwms.web.RESTClientUserService;

@SuppressWarnings("static-method")
public class RESTUserServiceTest {
	private static IUserService userService;
	
	@BeforeClass
	public static void beforeClass() {
		System.setProperty(Main.REST_PORT_PROP, "5678");
		
		Main.start(new StaticUserService());
		userService = new RESTClientUserService("localhost",
				PropertyUtil.getIntProperty(Main.REST_PORT_PROP, Main.REST_DEFAULT_PORT));
	}
	
	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}
	
	@Test
	public void getAllUsers() {
		Collection<User> users = userService.getAllUsers();
		Assert.assertNotNull("Users array", users);
	}
	
	@Test
	public void getUser() throws UserNotFoundException {
		int id = 0;
		User user = userService.getUser(id);
		Assert.assertNotNull("User found", user);
		Assert.assertEquals("User id", id, user.getId());
	}
	
	@Test
	public void crudUser() throws UserNotFoundException {
		User user = userService.createUser("Test", "dummyemaildoesnotexist@hotmail.com");
		Assert.assertNotNull("Created user", user);
		
		User created_user = userService.getUser(user.getId());
		Assert.assertEquals("Users equals", user, created_user);
		
		String new_email = "dummyemaildoesnotexist@gmail.com";
		userService.updateUser(user.getId(), user.getName(), new_email);
		User updated_user = userService.getUser(user.getId());
		Assert.assertEquals("Updated email", new_email, updated_user.getEmail());
		
		userService.deleteUser(user.getId());
		try {
			userService.getUser(user.getId());
			Assert.fail();
		} catch (UserNotFoundException e) {
		}
	}
	
	@Test
	public void upateUserNotFound() {
		try {
			userService.updateUser(999, "name", "email");
			Assert.fail();
		} catch (UserNotFoundException e) {
		}
	}
	
	@Test
	public void deleteUserNotFound() {
		try {
			userService.deleteUser(999);
			Assert.fail();
		} catch (UserNotFoundException e) {
		}
	}
}

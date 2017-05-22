package test.hwms;

import java.util.Collection;

import org.junit.*;

import spark.Spark;
import test.hwms.model.IUserService;
import test.hwms.model.User;
import test.hwms.model.UserNotFoundException;
import test.hwms.service.Main;
import test.hwms.util.PropertyUtil;
import test.hwms.web.RESTClientUserService;

@SuppressWarnings("static-method")
public class RESTUserServiceTest {
	private static IUserService userService;
	
	@BeforeClass
	public static void beforeClass() {
		System.setProperty(Main.REST_PORT_PROP, "5678");
		
		Main.main(null);
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
		users.forEach(System.out::println);
		Assert.assertNotNull("Users array", users);
	}
	
	@Test
	public void getUser() throws UserNotFoundException {
		int id = 0;
		User user = userService.getUser(id);
		System.out.println(user);
		Assert.assertNotNull("User found", user);
		Assert.assertEquals("User id", id, user.getId());
	}
	
	@Test
	public void createUser() throws UserNotFoundException {
		User user = userService.createUser("Test", "dummyemaildoesnotexist@hotmail.com");
		System.out.println(user);
		Assert.assertNotNull("User created", user);
		User user2 = userService.getUser(user.getId());
		Assert.assertEquals("Users equals", user, user2);
		User user3 = userService.deleteUser(user.getId());
		Assert.assertNotNull("User deleted", user3);
		Assert.assertEquals("Users equals", user, user3);
	}
	
	@Test
	public void upateUser() throws UserNotFoundException {
		User user = userService.createUser("Test", "dummyemaildoesnotexist@hotmail.com");
		System.out.println(user);
		Assert.assertNotNull("Created user", user);
		String new_email = "dummyemaildoesnotexist@gmail.com";
		User user2 = userService.updateUser(user.getId(), user.getName(), new_email);
		Assert.assertNotNull("Updated user", user);
		Assert.assertEquals("Updated email address", new_email, user2.getEmail());
		Assert.assertNotEquals("Users not equals", user, user2);
		User user3 = userService.getUser(user.getId());
		Assert.assertEquals("Users equals", user2, user3);
		User user4 = userService.deleteUser(user.getId());
		Assert.assertNotNull("User deleted", user4);
		Assert.assertEquals("Users equals", user2, user4);
	}
}

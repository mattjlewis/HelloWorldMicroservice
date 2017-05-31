package test.hwms.service;

import static spark.Spark.*;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import test.hwms.domain.IUserService;
import test.hwms.domain.User;
import test.hwms.domain.UserNotFoundException;

public class UserServiceController {
	private static final String JSON_CONTENT_TYPE = "application/json";
	private static final Gson GSON = new Gson();
	
	public static void init(final IUserService userService) {
		// List
		get("/service/users", (req, res) -> userService.getAllUsers(), GSON::toJson);
		// Get
		get("/service/user/:id", (req, res) -> userService.getUser(Integer.parseInt(req.params("id"))), GSON::toJson);
		// Create
		post("/service/user", (req, res) -> {
			User user = GSON.fromJson(req.body(), User.class);
			return userService.createUser(user.getName(), user.getEmail());	
		}, GSON::toJson);
		// Update
		put("/service/user/:id", (req, res) -> {
			System.out.println("put(" + req.params() + ", " + req.queryParams() + ", " + req.body() + ")");
			User user = GSON.fromJson(req.body(), User.class);
			userService.updateUser(Integer.parseInt(req.params("id")), user.getName(), user.getEmail());
			res.status(HttpServletResponse.SC_NO_CONTENT);
			return "";
		});
		// Delete
		delete("/service/user/:id", (req, res) -> { userService.deleteUser(Integer.parseInt(req.params("id"))); return ""; });
		
		after("/service/*", (req, res) -> res.type(JSON_CONTENT_TYPE));
		
		exception(UserNotFoundException.class, (exception, req, res) -> {
			res.type(JSON_CONTENT_TYPE);
			res.status(HttpServletResponse.SC_NOT_FOUND);
			res.body(GSON.toJson(new ResponseError(req.pathInfo(), exception)));
		});
	}
}

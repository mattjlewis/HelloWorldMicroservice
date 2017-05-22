package test.hwms.service;

import static spark.Spark.*;

import com.google.gson.Gson;

import test.hwms.model.IUserService;
import test.hwms.model.User;
import test.hwms.model.UserNotFoundException;

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
		put("/service/user", (req, res) -> {
			User user = GSON.fromJson(req.body(), User.class);
			return userService.updateUser(user.getId(), user.getName(), user.getEmail());
		}, GSON::toJson);
		// Delete
		delete("/service/user/:id", (req, res) -> userService.deleteUser(Integer.parseInt(req.params("id"))), GSON::toJson);
		
		after("/service/*", (req, res) -> res.type(JSON_CONTENT_TYPE));
		
		exception(UserNotFoundException.class, (exception, req, res) -> {
			res.type(JSON_CONTENT_TYPE);
			res.status(400);
			res.body(GSON.toJson(exception.getMessage()));
		});
	}
}

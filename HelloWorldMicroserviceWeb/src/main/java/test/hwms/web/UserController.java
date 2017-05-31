package test.hwms.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import spark.ModelAndView;
import test.hwms.domain.IUserService;
import test.hwms.domain.UserNotFoundException;

public class UserController {
	public static void init(final IUserService userService) {
		// List
		get("/users", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("page", "users");
			model.put("users", userService.getAllUsers());
			return Main.TEMPLATE_ENGINE.render(new ModelAndView(model, "users.ftl"));	
		});
		// Get
		get("/user/:id", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("page", "users");
			model.put("user", userService.getUser(Integer.parseInt(req.params("id"))));
			return Main.TEMPLATE_ENGINE.render(new ModelAndView(model, "user.ftl"));	
		});
		// Create
		post("/user", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("page", "users");
			model.put("user", userService.createUser(req.queryParams("name"), req.queryParams("email")));
			model.put("users", userService.getAllUsers());
			return Main.TEMPLATE_ENGINE.render(new ModelAndView(model, "users.ftl"));	
		});
		// Update
		post("/user/:id", (req, res) -> {
			userService.updateUser(Integer.parseInt(req.params("id")), req.queryParams("name"), req.queryParams("email"));
			Map<String, Object> model = new HashMap<>();
			model.put("page", "users");
			model.put("users", userService.getAllUsers());
			return Main.TEMPLATE_ENGINE.render(new ModelAndView(model, "users.ftl"));	
		});
		// Delete
		get("/user/delete/:id", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("page", "users");
			model.put("users", userService.getAllUsers());
			return Main.TEMPLATE_ENGINE.render(new ModelAndView(model, "users.ftl"));	
		});
		
		exception(UserNotFoundException.class, (exception, req, res) -> {
			res.status(HttpServletResponse.SC_NOT_FOUND);
		});
	}
}

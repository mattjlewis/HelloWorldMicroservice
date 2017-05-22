package test.hwms.web;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import test.hwms.model.IUserService;

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
			Map<String, Object> model = new HashMap<>();
			model.put("page", "users");
			model.put("user", userService.updateUser(Integer.parseInt(req.params("id")), req.queryParams("name"), req.queryParams("email")));
			model.put("users", userService.getAllUsers());
			return Main.TEMPLATE_ENGINE.render(new ModelAndView(model, "users.ftl"));	
		});
	}
}

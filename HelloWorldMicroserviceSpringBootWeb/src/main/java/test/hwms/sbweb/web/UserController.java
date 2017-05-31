package test.hwms.sbweb.web;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import test.hwms.domain.IUserService;
import test.hwms.domain.User;
import test.hwms.domain.UserNotFoundException;

@Controller
public class UserController {
	private IUserService userService;
	
	public UserController(IUserService userService) {
		this.userService = userService;
	}
	
	// List
	@GetMapping("/users")
	String getUsers(Map<String, Object> model) {
		model.put("page", "users");
		model.put("users", userService.getAllUsers());
		return "users";
	}
	
	// Get
	@GetMapping("/user/{id}")
	String getUser(@PathVariable("id") Integer id, Map<String, Object> model) throws UserNotFoundException {
		model.put("page", "users");
		if (id == null) {
			return "redirect:/users";
		}
		model.put("user", userService.getUser(id.intValue()));
		return "user";
	}
	
	// Create
	@PostMapping("/user")
	String createUser(@ModelAttribute User user, Map<String, Object> model) {
		System.out.println("createUser(" + user + ")");
		model.put("page", "users");
		model.put("user", userService.createUser(user.getName(), user.getEmail()));
		model.put("users", userService.getAllUsers());
		return "users";
	}
	
	// Update
	@PostMapping("/user/{id}")
	String updateUser(@PathVariable("id") Integer id, @ModelAttribute User user, Map<String, Object> model) throws UserNotFoundException {
		System.out.println("Update user " + id + ", " + user);
		model.put("page", "users");
		userService.updateUser(id.intValue(), user.getName(), user.getEmail());
		return "redirect:/users";
	}
	
	@SuppressWarnings("static-method")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	ModelAndView userNotFound(UserNotFoundException e) {
		System.out.println("userNotFound!");
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("status", Integer.valueOf(404));
		mav.addObject("exception", e);
		mav.addObject("message", e.getMessage());
		return mav;
	}

	/*
	@SuppressWarnings("static-method")
	@ExceptionHandler(UserNotFoundException.class)
	String userNotFound(UserNotFoundException e, Map<String, Object> model) {
		System.out.println("userNotFound!");
		model.put("status", Integer.valueOf(404));
		model.put("message", e.getMessage());
		return "error";
	}
	*/
}

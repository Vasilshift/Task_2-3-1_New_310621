package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UsersController {

	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user")
	public String userPage(Model model, int id) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		return "user";
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("users", userService.index());
		return "people/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.getUser(id));
		return "people/show";
	}

	@GetMapping("/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "people/new";
	}

	@PostMapping("/")
	public String add(@ModelAttribute("user") User user) {
		userService.add(user);
		return "redirect:/";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.getUser(id));
		return "people/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") User user) {
		userService.update(user);
		return "redirect:/";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

}
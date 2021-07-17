package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/people")
public class UsersController {

	private final UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user")
	public String userPage(Model model, int id) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		return "user";
	}

	@GetMapping()
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

	@PostMapping()
	public String add(@ModelAttribute("user") User user) {
		userService.add(user);
		return "redirect:/people";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.getUser(id));
		return "people/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") User user,
						 @PathVariable("id") int id) {
		userService.update(user, id);
		return "redirect:/people";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/people";
	}

}
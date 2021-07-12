package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/people")
public class UsersController {

	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.getUser(id));
		return "people/show";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.getUser(id));
		return "people/show";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") User user) {
		userService.update(user);
		return "people/show";
	}

	@DeleteMapping("/{id}/delete")
	public String delete(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/admin";
	}

}
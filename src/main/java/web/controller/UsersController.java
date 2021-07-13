package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.UserService;

@Controller
@RequestMapping("/people")
public class UsersController {

	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.getUser(id));
		return "people/show";
	}

//
//	@GetMapping("/")
//	public String index(Model model) {
//		model.addAttribute("users", userService.index());
//		return "people/index";
//	}
//
//	@GetMapping("/{id}")
//	public String show(@PathVariable("id") int id, Model model) {
//		model.addAttribute("user", userService.getUser(id));
//		return "people/show";
//	}
//
//	@GetMapping("/new")
//	public String newUser(@ModelAttribute("user") User user) {
//		return "people/new";
//	}
//
//	@PostMapping("/")
//	public String add(@ModelAttribute("user") User user) {
//		userService.add(user);
//		return "redirect:/";
//	}
//
//	@GetMapping("/{id}/edit")
//	public String edit(Model model, @PathVariable("id") int id) {
//		model.addAttribute("user", userService.getUser(id));
//		return "people/edit";
//	}
//
////	@RequestMapping(method = RequestMethod.PATCH)
//	@PatchMapping("/{id}")
//	public String update(@ModelAttribute("user") User user) {
//		userService.update(user);
//		return "redirect:/";
//	}
//
//	@DeleteMapping("/{id}")
//	public String delete(@PathVariable("id") int id) {
//		userService.delete(id);
//		return "redirect:/";
//	}


}
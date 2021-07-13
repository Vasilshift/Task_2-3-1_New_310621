package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
@RequestMapping("/people")
public class UsersController {

	private final UserService userService;

	@PersistenceContext
	private EntityManager entityManager;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public String showForUser(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.getUser(id));
		return "showForUser";
	}

	public String findUserByUsername(String username) {
		return String.valueOf(entityManager
				.createQuery("select u from User u where u.username = :username", User.class)
				.setParameter("username", username)
				.getSingleResult());
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
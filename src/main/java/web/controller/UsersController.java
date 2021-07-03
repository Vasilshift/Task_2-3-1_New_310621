package web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

@Controller
@RequestMapping("/people")
public class UsersController {

	private final UserDao userDao;

	@Autowired
	public UsersController(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("users", userDao.index());
		return "people/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userDao.show(id));
		return "people/show";
	}

	@GetMapping("/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "people/new";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") User user) {
		userDao.save(user);
		return "redirect:/people";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userDao.show(id));
		return "people/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
						 @PathVariable("id") int id) {
		if(bindingResult.hasErrors())
			return "people/edit";
		userDao.update(id, user);
		return "redirect:/people";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		userDao.delete(id);
		return "redirect:/people";
	}


}
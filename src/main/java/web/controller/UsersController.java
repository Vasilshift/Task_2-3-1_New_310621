package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {

	private final UserDao userDao;

	public UsersController(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("users", userDao.index());
		return "index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userDao.show(id));
		return "show";
	}

	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "new";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") User user) {
		userDao.save(user);
		return "redirect:/";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userDao.show(id));
		return "/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
		userDao.update(id, user);
		return "redirect:/";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		userDao.delete(id);
		return "redirect:/";
	}


}
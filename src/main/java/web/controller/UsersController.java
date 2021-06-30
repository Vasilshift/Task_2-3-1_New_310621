package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.dao.UserDao;

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

//		List<String> messages = new ArrayList<>();
//		messages.add("Show all users from table ");
//		model.addAttribute("messages", messages);

		return "index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userDao.show(id));

		return "show";
	}

	
}
package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

	@GetMapping("/user")
	public String userPage(Model model, Principal principal) {
		User user = userService.findUserByUsername(principal.getName());
		model.addAttribute("user", user);
		return "user";
	}

    @RequestMapping(value = "login", method = RequestMethod.GET)
    	public String loginPage() {
        	return "login";
    }

}
package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
//import web.service.RoleService;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;

    public AdminsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(Model model, int id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
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

    //	@RequestMapping(method = RequestMethod.PATCH)
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







//    private UserService userService;
////    private RoleService roleService;
////
////    @Autowired
////    public AdminsController(UserService userService) {
////        this.userService = userService;
////    }
////
////    @Autowired
////    public AdminsController(RoleService roleService) {
////        this.roleService = roleService;
////    }
//
//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("users", userService.index());
//        return "people/index";
//    }
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.show(id));
//        return "people/show";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("user", userService.show(id));
//        return "people/edit";
//    }
//
//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "people/new";
//    }
//
//    @PostMapping()
//    public String add(@ModelAttribute("user") User user) {
//        userService.add(user);
//        return "redirect:/";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") User user,
//                         @PathVariable("id") int id) {
//        userService.update(user, id);
//        return "redirect:/";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        userService.delete(id);
//        return "redirect:/";
//    }
//
}
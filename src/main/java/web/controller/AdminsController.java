package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "admin/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("roleView") Set<String> roleView ) {
        if (roleView.contains("roleAdmin")) {
            userService.add(userService.addRoles(user, roleService.getRoleByRolename("ROLE_ADMIN")));
        } else if (roleView.contains("roleUser")) {
            userService.add(userService.addRoles(user, roleService.getRoleByRolename("ROLE_USER")));
        }
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));

        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id,
                         @RequestParam("roleView") Set<String> roleView) {

        if (roleView.contains("roleAdmin")) {
            userService.update(userService.addRoles(user, roleService.getRoleByRolename("ROLE_ADMIN")), id);
        }
        if (roleView.contains("roleUser")) {
            userService.update(userService.addRoles(user, roleService.getRoleByRolename("ROLE_USER")), id);
        }

        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

//    @GetMapping("/{id}/profile")
//    public String showUserProfileModal(@PathVariable("id") Long userId, Model model, RedirectAttributes attributes) {
//        try {
//            model.addAttribute("allRoles", userService.getSetOfRoles());
//            model.addAttribute("user", userService.findUser(userId));
//            return "fragments/user-form";
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
//        }
//    }

}

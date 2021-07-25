package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    @Autowired
    private final UserService userService;
    private final RoleService roleService;

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

    @PostMapping("/new")
    public String add(@ModelAttribute("user") User user,
                      @RequestParam(required = false, name = "roleAdmin") String roleAdmin
                        ) {
        if (roleAdmin != null) {
            userService.add(userService.addRoles(user, roleService.getRoleByRolename("ROLE_ADMIN")));
        } else {
            userService.add(userService.addRoles(user, roleService.getRoleByRolename("ROLE_USER")));
        }
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));

        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id,
                         @RequestParam(required = false, name = "roleAdmin") String roleAdmin,
                         @RequestParam(required = false, name = "roleUser") String roleUser) {

        if (roleAdmin != null) {
            userService.update(userService.addRoles(user, roleService.getRoleByRolename("ROLE_ADMIN")), id);
        }
        if (roleUser != null) {
            userService.update(userService.addRoles(user, roleService.getRoleByRolename("ROLE_USER")), id);
        }

        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

}

package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/admin/")
public class AdminsController {

    private final UserService userService;
    //private final RoleService roleService;

    @Autowired
    public AdminsController(UserService userService) {
        this.userService = userService;
        //this.roleService = roleService;
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

    @PostMapping()
    public String add(@ModelAttribute("user") User user,
                      @RequestParam(required = false) String roleUser,
                      @RequestParam(required = false) String roleAdmin
                        ) {
        //find user by username
        User userFromDB = userService.findUserByUsername(user.getUsername());
        //user.setRoles(Collections.singleton(new Role(2, "ROLE_USER")));
        userFromDB.setRoles(2, roleUser);


//        if (roleAdmin != null) {
//            //get user_id for insert into table users_roles
//            int user_id = user.getId();
//            int role_id = 1;
//
//            entity"insert into users_roles ()"
//        }

        userService.add(user);

        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(user, id);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

}

package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

//import web.service.RoleService;

@Controller
//@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")

public class AdminsController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String userPage(Model model, int id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user";
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
                      @RequestParam(required = false) String roleAdmin,
                      @RequestParam(required = false) String roleGuest) {
        Set<Role> roles = new HashSet<>();
        //roles.add(roleService.getDefaultRole());

        if (roleAdmin != null) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        }
        if (roleGuest != null) {
            roles.add(roleService.getRoleByName("ROLE_GUEST"));
        }
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/edit";
    }

    //	@RequestMapping(method = RequestMethod.PATCH)
    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/index";
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

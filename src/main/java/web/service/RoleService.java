package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface RoleService {

    Role getRoleByRolename(String rolename);

    List<Role> RolesList();

    void setupRoles(User user, String roleAdmin, String roleUser);

    void addRole(Role role);

    void updateRole(Role role);

}

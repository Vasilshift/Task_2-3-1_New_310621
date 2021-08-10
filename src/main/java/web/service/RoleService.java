package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;


public interface RoleService {

    Role getRoleByName(String name);

    List<Role> getRolesList();

    void addRole(Role role);

    void updateRole(Role role);

    Role getRoleById(Long id);

    Set<Role> updateRoles(String[] roleView);

    void addRolesToUser(User user, String[] roleView);
}

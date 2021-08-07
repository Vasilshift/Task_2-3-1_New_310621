package web.service;

import web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Role getRoleByRolename(String rolename);

    List<Role> getRolesList();

    void addRole(Role role);

    void updateRole(Role role);

    Role getRoleById(Long id);

    Set<Role> updateRoles(String[] roleView);
}

package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {

    Role getRoleByRolename(String rolename);

    List<Role> getRolesList();

    void addRole(Role role);

    void updateRole(Role role);

    Role getRoleById(Long id);
}

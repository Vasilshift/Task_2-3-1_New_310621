package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {

    Role getRoleByRolename(String rolename);

    List<Role> RolesList();

    //void setupRoles(User user, String roleAdmin, String roleUser);

    void addRole(Role role);

}

package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Role getRoleByRolename(String rolename) {
        return roleDao.getRoleByRolename(rolename);
    }

    @Transactional
    @Override
    public List<Role> RolesList() {
        return roleDao.RolesList();
    }

    @Transactional
    @Override
    public void setupRoles(User user, String roleAdmin, String roleUser) {
        Set<Role> roles = new HashSet<>();

        if (roleAdmin.equals("ROLE_ADMIN")) {
            roles.add(getRoleByRolename("ROLE_ADMIN"));
        }
        if (roleUser.equals("ROLE_USER")) {
            roles.add(getRoleByRolename("ROLE_USER"));
        }
        user.setRoles(roles);

        System.out.println(user.getRoles());
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }
}

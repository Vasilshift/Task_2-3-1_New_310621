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
    private RoleService roleService;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }


    public RoleServiceImp(RoleService roleService) {
        this.roleService = roleService;
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
    public List<Role> getRolesList() {
        return roleDao.getRolesList();
    }

    @Transactional
    @Override
    public Role setupRoles(User user, String roleAdmin, String roleUser) {
        Set<Role> roles = new HashSet<>();

        if (roleAdmin.equals("ROLE_ADMIN")) {
            roles.add(getRoleByRolename("ROLE_ADMIN"));
        }
        if (roleUser.equals("ROLE_USER")) {
            roles.add(getRoleByRolename("ROLE_USER"));
        }

        if (roleAdmin.equals("ROLE_ADMIN") & roleAdmin.equals("ROLE_USER")) {
            roles.add(getRoleByRolename("ROLE_USER"));
            roles.add(getRoleByRolename("ROLE_ADMIN"));
        }
        user.setRoles(roles);

        System.out.println(user.getRoles());
        return null;
    }


    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }


    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Transactional
    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

//    @Override
//    public void updateRoles(String[] roleView) {
//        Set<Role> roleList = new HashSet<>();
//        for (String role : roleView) {
//
//            if (role.equals("ROLE_ADMIN")) {
//                roleList.add(roleService.getRoleByRolename("ROLE_ADMIN"));
//            } else if (role.equals("ROLE_USER")) {
//                roleList.add(roleService.getRoleByRolename("ROLE_USER"));
//            }
//        }
//        roles.setRoles(roleList);
//    }
}

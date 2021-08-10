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
    private RoleService roleService;

    public RoleServiceImp(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public RoleServiceImp( RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getRolesList() {
        return roleDao.getRolesList();
    }

    @Transactional(readOnly = true)
    @Override
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

    @Transactional
    @Override
    public Set<Role> updateRoles(String[] roleView) {
        Set<Role> roleList = new HashSet<>();
        for (String role : roleView) {
            if (role.equals("ROLE_ADMIN")) {
                roleList.add(roleService.getRoleByName("ROLE_ADMIN"));
            } else if (role.equals("ROLE_USER")) {
                roleList.add(roleService.getRoleByName("ROLE_USER"));
            }
        }
        return roleList;
    }
    @Override
    public void addRolesToUser(User user, String[] roleView) {
        Set<Role> roleList = new HashSet<>();
        for (String role : roleView) {
            if (role.equals("ROLE_ADMIN")) {
                roleList.add(roleService.getRoleByName("ROLE_ADMIN"));
            } else if (role.equals("ROLE_USER")) {
                roleList.add(roleService.getRoleByName("ROLE_USER"));
            }
        }
        user.setRoles(roleList);
    }
    
}

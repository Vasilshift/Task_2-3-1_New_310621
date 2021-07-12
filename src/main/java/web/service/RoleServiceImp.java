package web.service;

import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.List;

public class RoleServiceImp implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional(readOnly=true)
    @Override
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Role> getRolesList() {
        return roleDao.getRolesList();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.(getRoleByName(name));
    }
}

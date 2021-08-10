package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private UserDao userDao;
    private RoleService roleService;

    @Autowired
    public void UserServiceImp(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    public List<User> index() {
        return userDao.index();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(User user, int id) {
        userDao.update(user, id);
    }

    @Override
    @Transactional
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userDao.findUserByUsername(username);
        return User.fromUser(user);
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

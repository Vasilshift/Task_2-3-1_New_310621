package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

public class UserServiceImp implements UserService{

    private final UserDao userDao;

    public UserServiceImp(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public List<User> index() {
        return userDao.index();
    }

    @Transactional
    @Override
    public User show(int id) {
        return userDao.show(id);
    }

    @Override
    public void add(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(User user,int id) {
        userDao.update(id, user);
    }
}

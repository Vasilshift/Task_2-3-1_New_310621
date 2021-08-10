package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> index();

    User getUser(int id);

    void add(User user);

    void delete(int id);

    void update(User user, int id);

    User findUserByUsername(String username);

    //void addRolesToUser(User user, String[] roleView);

}
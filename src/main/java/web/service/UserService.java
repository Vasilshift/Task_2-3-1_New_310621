package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    List<User> index();

    User show(int id);

    void add(User user);

    void delete(int id);

    void update(User user, int id);

}
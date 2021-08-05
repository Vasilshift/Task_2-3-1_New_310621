package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> index();

    User getUser(int id);

    void add(User user);

    void delete(int id);

    void update(User user, int id);

    User findUserByUsername(String username);

    User addRoles(User user, Role role);

    Set<Role> getSetOfRoles(List<String> roles);

}
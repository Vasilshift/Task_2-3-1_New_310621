package web.service;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

public interface UserService {

    //List<User> allUsers();

    //@Transactional
    List<User> index();

    User show(int id);

    void add(User user);

    //@Transactional
    //void update(User user);

    void delete(int id);

    void update(User user,int id);
}

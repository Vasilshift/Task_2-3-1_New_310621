package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;
import java.util.List;

public interface UserDao {

    List<User> index();

    User show (int id);

    void add(User user);

    void delete(int id);

    void update(User user, int id);

}


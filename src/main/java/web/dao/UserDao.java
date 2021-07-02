package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    private static int USER_COUNT;
    private List<User> users;

    {
        users = new ArrayList<>();
        users.add(new User(++USER_COUNT, "Tom", "Tailor"));
        users.add(new User(++USER_COUNT, "Mike", "Hook"));

    }

    public List<User> index() {
        return users;
    }

    public User show(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
    }

    public void update(int id, User updateUser) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setName(updateUser.getName());
        userToBeUpdated.setSurName(updateUser.getSurName());
    }

    public void delete(int id) {
        users.removeIf(p -> p.getId() == id);
    }

}

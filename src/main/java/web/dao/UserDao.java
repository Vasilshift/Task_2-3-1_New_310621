package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import web.model.User;
import java.util.List;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int USER_COUNT;
    private List<User> users;


    public List<User> index() {
        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
    }

    public User show(int id) {
        return jdbcTemplate.query("select * from users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("insert into users VALUES(1, ?, ?)", user.getName(), user.getSurName());
    }

    public void update(int id, User updateUser) {
        jdbcTemplate.update("update users SET name=?, surname=? WHERE id=?", updateUser.getName(), updateUser.getSurName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from users WHERE id=?", id);
    }
}

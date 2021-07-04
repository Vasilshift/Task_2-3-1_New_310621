package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import web.model.User;
import java.util.List;

@Component
public interface UserDao {

    List<User> getAll();

    User getOne(int id);

    void add(User user);

    void delete(int id);

    void update(User user, int id);
}

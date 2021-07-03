package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import web.model.User;

import java.util.List;

public class UserDaoImp implements UserDao{

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("Unchecked")
    @Override
    public List<User> allUsers() {
// return new ArrayList<>(users.values());
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("From User").list();
    }

    @Override
    public User show(int id) {
        String HQL = "from User where User.id = :id";
// ArrayList<User> users2 = new ArrayList<>(users.values());
// return users2.stream()
// .filter(user -> user.getId() == id)
// .findAny()
// .orElse(null);
        User user = sessionFactory.getCurrentSession()
                .createQuery(HQL, User.class)
                .setParameter("id", id)
                .uniqueResult();

        return user;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(int id, User updateUser) {

    }

    @Override
    public void delete(int id) {
        String HQL = "delete from User where User.id = :id";
        sessionFactory.getCurrentSession()
                .createQuery(HQL, User.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public void update(User user, int id) {
        User userToUpdate = show(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setSurName(user.getSurName());
    }
}

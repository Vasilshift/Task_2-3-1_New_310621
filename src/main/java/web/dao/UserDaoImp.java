package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImp(){}

    @SuppressWarnings("Unchecked")
    @Override
    public List<User> index() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        TypedQuery<User> query = entityManager.createQuery("From User where id=:id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    //@Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    //@Transactional
    @Override
    public void delete(int id) {
        TypedQuery<User> query = entityManager.createQuery("delete from User where id=:id", User.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    //@Transactional
    @Override
    public void update(User user, int id) {
        User userToUpdate = show(id);
        userToUpdate.setUsername(user.getUsername());
    }
}
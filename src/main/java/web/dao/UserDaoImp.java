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

    public UserDaoImp() {}

    @SuppressWarnings("Unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<User> index() {
        return entityManager.createQuery("From User", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(int id) {
        TypedQuery<User> query = entityManager.createQuery("From User where id=:id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        entityManager.remove(getUser(id));
    }

    @Transactional
    @Override
    public void update(User user, int id) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public User findUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("From User where username=:username", User.class);
        query.setParameter("username", username);
        return query.getResultList().stream().findAny().orElse(null);
    }


}
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
    @Transactional(readOnly = true)
    @Override
    public List<User> index() {
        return entityManager.createQuery("From User", User.class).getResultList();
    }

//    @Override
//    @Transactional(readOnly = true)
//    public User getUser(int id) {
//        return entityManager.find(User.class, id);
//    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(int id) {
        TypedQuery<User> query = entityManager.createQuery("From User where id=:id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(id);
    }

    @Override
    public void update(User user, int id) {
        entityManager.merge(id);
    }
}
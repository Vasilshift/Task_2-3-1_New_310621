package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
    @Override
    public User show(int id) {
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
        TypedQuery<User> query = entityManager.createQuery("delete from User where id =:id",User.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }
    @Override
    public void update(User user, int id) {
        User userToUpdate = show(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setSurName(user.getSurName());
    }
}
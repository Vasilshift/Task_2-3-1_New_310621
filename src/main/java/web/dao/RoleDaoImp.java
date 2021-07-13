package web.dao;

import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//    @Override
//    public Role getRoleByUserName(String username) {
//        return entityManager
//                .createQuery("select r from Role r where r.username= :username", Role.class)
//                .setParameter("username", username)
//                .getSingleResult();
//    }


    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class,id);
    }

    @Override
    public List<Role> getRolesList() {
        return entityManager.createQuery("From Role", Role.class).getResultList();
    }
}

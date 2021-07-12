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

    @Override
    public Role getRoleByName(String name) {
        return entityManager
                .createQuery("select r from Role r where r.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class,id);
    }

    @Override
    public List<Role> getRolesList() {
        return entityManager.createQuery("From Role", Role.class).getResultList();
    }
}

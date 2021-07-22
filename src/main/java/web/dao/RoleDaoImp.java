package web.dao;

import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImp() {
    }

    @Override
    public Role getRoleByRolename(String rolename) {
        return entityManager.createQuery("select r from Role r where r.rolename = :username", Role.class)
                .setParameter("username", rolename)
                .getSingleResult();
    }

    @Override
    public List<Role> RolesList() {
        return entityManager.createQuery("From Role", Role.class).getResultList();
    }

//    @Override
//    public void setupRoles(User user, String roleAdmin, String roleUser) {
//
//    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
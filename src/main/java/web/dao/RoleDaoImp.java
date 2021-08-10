package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImp() {
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select r from Role r where r.name = :username", Role.class)
                .setParameter("username", name)
                .getSingleResult();
    }

    @Override
    public List<Role> getRolesList() {
        return entityManager.createQuery("From Role", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

}

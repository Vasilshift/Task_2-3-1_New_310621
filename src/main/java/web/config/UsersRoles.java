package web.config;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import web.model.Role;

import java.util.List;

public class UsersRoles {

    @Autowired
    public LocalSessionFactoryBean factoryBean;

    public List<Role> getRolesList() {
        Session session = factoryBean.getObject().getCurrentSession();

        return session.createQuery("from Role").getResultList();
    }

    public Role getRoleById(int id) {
        Session session = factoryBean.getObject().getCurrentSession();
        return session.find(Role.class, id);
    }
}

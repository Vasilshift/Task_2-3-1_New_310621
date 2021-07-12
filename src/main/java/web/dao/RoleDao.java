package web.dao;

import web.model.Role;
import java.util.List;

public interface RoleDao {
    Role getRoleByName(String name);

    Role getRoleById(int id);

    List<Role> getRolesList();

    //Role getDefaultRole();
}


//package web.dao;
//
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import web.model.Role;
//
//import java.util.List;
//
//public class RoleDao {
//
//    @Autowired
//    private LocalSessionFactoryBean factoryBean;
//
//    public List<Role> getRolesList() {
//        Session session = factoryBean.getObject().getCurrentSession();
//
//        return session.createQuery("from Role").getResultList();
//    }
//
//    public Role getRoleById(int id) {
//        Session session = factoryBean.getObject().getCurrentSession();
//        return session.find(Role.class, id);
//    }
//}

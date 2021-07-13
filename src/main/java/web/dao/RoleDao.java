package web.dao;

import web.model.Role;
import java.util.List;

public interface RoleDao {

    //Role getRoleByUserName(String username);

    Role getRoleById(int id);

    List<Role> getRolesList();

    //Role getDefaultRole();
}




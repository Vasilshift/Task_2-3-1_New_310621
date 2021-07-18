package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    public Role(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(int id, String rolename, Collection<User> users) {
        this.id = id;
        this.rolename = rolename;
        this.users = users;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return rolename;
    }
}

package web.model;

import javassist.bytecode.MethodParametersAttribute;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    public static MethodParametersAttribute USER;
    public static MethodParametersAttribute ADMIN;

    public Role(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(int id, String username, Collection<User> users) {
        this.id = id;
        this.username = username;
        this.users = users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
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
        return username;
    }
}

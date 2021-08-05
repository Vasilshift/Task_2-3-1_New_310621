package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    public Role(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role")
    private String name;

//    @Transient
//    @ManyToMany(mappedBy = "roles")
//    private Collection<User> users;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
        //this.users = users;
    }

    public String getRolename() {
        return name;
    }

    public void setRolename(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    //public Collection<User> getUsers() {
//        return users;
//    }
//
//    //public void setUsers(Collection<User> users) {
//        this.users = users;
//    }

    @Override
    public String getAuthority() {
        return name;
    }
}

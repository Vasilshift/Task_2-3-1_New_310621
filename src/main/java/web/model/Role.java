//package web.model;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "roles")
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int role_id;
//
//    @Column(name = "name")
//    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
//
//    @Column(name = "user_id")
//    private String user_id;
//
//    public Role(){
//    }
//
//    public int getId() {
//        return role_id;
//    }
//
//    public void setId(int id) {
//        this.role_id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    @Override
//    public String toString() {
//        return "Role{" +
//                "role_id=" + role_id +
//                ", name='" + name + '\'' +
//                ", users=" + users +
//                '}';
//    }
//}

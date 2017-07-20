package com.valsoft.model;


        import javax.persistence.*;
        import java.util.Set;


/**
 * Created by Asus on 18.06.2017.
 */

public enum Role {
    USER, MODERATOR, ADMIN;

    Role() {

    }
}

//@Entity
//@Table(name = "ROLE")
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ROLE_ID")
//    private Long id;
//
//    @Column(name = "NAME")
//    @Author({"ADMINISTRATOR","MODERATOR","USER"})
//    private String name;
//
//    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<User> userSet;
//
//    public Set<User> getUserSet() {
//        return userSet;
//    }
//
//    public void setUserSet(Set<User> userSet) {
//        this.userSet = userSet;
//    }*/
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
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
//    @Override
//    public String toString() {
//        return "Role{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
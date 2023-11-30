package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.SQLType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "users")
@Getter
@Setter
@Data
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return userId != null && Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public User() {
    }

    public User(String userName, String password, Role role) {
        lastName = "";
        firstName = "";
        patronymic = "";
        this.userName = userName;
        this.password = password;
        this.role = role;
        position = new Position();
        department = new Department();
    }


//    public boolean isAdmin() {
//        for (Role role : roles) {
//            if (role.name.equals("ROLE_ADMIN")) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void addRole(Role role){
//        if(roles==null){
//            roles = new HashSet<>();
//        }
//        roles.add(role);
//    }
//
//    public String getFIO(){
//        return surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ".";
//    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", position=" + position +
                ", role=" + role +
                ", department=" + department +
                '}';
    }


    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String encode) {
        password = encode;
    }

    public void setUserId(Long id) {
        userId = id;
    }
}

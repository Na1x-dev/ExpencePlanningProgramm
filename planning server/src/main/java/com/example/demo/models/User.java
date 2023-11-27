package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    @Column(name = "username")
    @NonNull String username;

    @Column(name = "password")
    @NonNull String password;

    @Column(name = "surname")
    @NonNull String surname;

    @Column(name = "name")
    @NonNull String name;

    @Column(name = "patronymic")
    @NonNull String patronymic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false, referencedColumnName = "position_id")
    @NonNull
    Position position;

    @ManyToMany
    @NonNull
    @ToString.Exclude
    Set<Role> roles;

    @Transient
    @NonNull String passwordConfirm;

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

    public User(String username, String password, Role role, String passwordConfirm) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        roles = new HashSet<>();
        roles.add(role);
        name = "";
        surname = "";
        patronymic = "";
        new Position();
    }


    public boolean isAdmin() {
        for (Role role : roles) {
            if (role.name.equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

    public void addRole(Role role){
        if(roles==null){
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    public String getFIO(){
        return surname + " " + name.charAt(0) + "." + patronymic.charAt(0) + ".";
    }

}

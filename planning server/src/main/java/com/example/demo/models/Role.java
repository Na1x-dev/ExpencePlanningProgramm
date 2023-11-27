package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;


@Data
@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NonNull Long roleId;

    @NonNull String name;

    @ManyToMany(mappedBy = "roles")
    @NonNull Set<User> users;

    public Role() {
        name = "";
    }

    public Role(String roleName){
        name = roleName;
    }
}
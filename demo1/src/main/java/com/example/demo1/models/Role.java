package com.example.demo1.models;
import lombok.Data;


@Data
public class Role {
    private Long roleId;

    private String roleName;

    public Role() {
        roleName = "";
    }

    public Role(String roleName){
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

}
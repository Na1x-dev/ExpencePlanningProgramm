package com.example.demo1.models;

import lombok.*;

@Data
public class User {

//    @JsonProperty("userId")
    private Long id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private String userName;

    private String password;

    private Position position;

    private Role role;

    private Department department;

    public User() {
        id = 0L;
        lastName = "";
        firstName = "";
        patronymic = "";
        userName = "";
        password = "";
        position = new Position();
        role = new Role();
        department = new Department();
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
                "userId=" + id +
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

}

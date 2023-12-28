package com.example.demo1.models;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class User implements Model {

    //    @JsonProperty("userId")
    private Long userId;

    private String lastName;

    private String firstName;

    private String patronymic;

    private String userName;

    private String password;

    private Position position;

    private Role role;

    private Department department;


    public User() {
        userId = 0L;
        lastName = "";
        firstName = "";
        patronymic = "";
        userName = "";
        password = "";
        position = new Position();
        role = new Role();
        department = new Department();
    }

    public User(String userName, String password) {
        lastName = "";
        firstName = "";
        patronymic = "";
        this.userName = userName;
        this.password = password;
        role = null;
        position = null;
        department = null;
    }

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

    public String toComboBox(){
        return (userId + ". " + lastName + " " + (firstName.isEmpty() ? "" : (firstName.charAt(0) + ".")) + (patronymic.isEmpty() ? "" : (patronymic.charAt(0) + ".")));

    }

    public List<String> getFieldsForCreate() {
        List<String> listFields = new ArrayList<>();
        listFields.add("firstName");
        listFields.add("firstName");
        listFields.add("patronymic");
        listFields.add("userName");
        listFields.add("password");
        return listFields;
    }

    public List<String> getFieldsNameForCreate() {
        List<String> listFieldsName = new ArrayList<>();
        listFieldsName.add("Фамилия");
        listFieldsName.add("Имя");
        listFieldsName.add("Отчество");
        listFieldsName.add("Логин");
        listFieldsName.add("Пароль");
        return listFieldsName;
    }


}

package com.golovin.springboot.springboot311.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле Имя не должно быть пустым!")
    @Size(min = 2, max = 30, message = "Поле Имя должно содержать от 2 до 30 символов")
    @Column(name = "firstName")
    private String firstName;
    @NotEmpty(message = "Поле Фамилия не должно быть пустым!")
    @Size(min = 2, max = 30, message = "Поле Фамилия должно содержать от 2 до 30 символов")
    @Column(name = "lastName")
    private String lastName;
    @NotEmpty(message = "Поле Город не должно быть пустым!")
    @Size(min = 2, max = 30, message = "Поле Город должно содержать от 2 до 30 символов")
    @Column(name = "userCity")
    private String userCity;

    public User() {
    }

    public User(String firstName, String lastName, String userCity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userCity = userCity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userCity='" + userCity + '\'' +
                '}';
    }
}
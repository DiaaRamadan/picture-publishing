package com.picture_publishing.entities;

import com.picture_publishing.enums.UserType;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 128, nullable = false, unique = true)
    @Email(message = "Please enter valid email")
    @NotNull(message = "Email can not be null")
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @Column(length = 64, nullable = false)
    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Password can not be empty")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type.name() +
                '}';
    }
}

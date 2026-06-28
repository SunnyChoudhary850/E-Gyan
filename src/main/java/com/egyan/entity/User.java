package com.egyan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/*
@Entity
public class User {
@Id
@GeneratedValue(strategy=GeneralType.IDENTITY)
private Long id;
private String name;
private String email;
private String password;
private String role;

 */

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String role; // ADMIN, FACULTY, STUDENT
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private List<Course> enrolledCourses;
    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Course> courses;
    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters

}
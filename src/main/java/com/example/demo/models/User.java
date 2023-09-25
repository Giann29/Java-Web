package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="users")
public class User {
    @Id @Getter @Setter @Column(name="id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter @Column(name="name")
    private String name;
    @Getter @Setter @Column (name="surname")
    private String surname;
    @Getter @Setter @Column (name="email")
    private String email;
    @Getter @Setter @Column (name="phonenumber")
    private String phoneNumber;
    @Getter @Setter @Column (name="password")
    private String password;

}

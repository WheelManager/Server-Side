package com.hackdead.wheelmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",nullable = false,length = 50)
    private String username;
    @Column(name = "password",nullable = false,length = 25)
    private String password;
    @Column(name = "email",nullable = false,length = 150)
    private String email;
    @Column(name = "name",nullable = false,length = 25)
    private String name;
    @Column(name = "lastname",nullable = false,length = 30)
    private String lastname;
    @Column(name = "image_url",nullable = false)
    private String imageUrl;
    @Column(name = "dni",nullable = false,length = 8)
    private String  dni;
    @Column(name = "gender",nullable = false,length = 50)
    private String gender;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day",nullable = false)
    private Date birthDay;
}

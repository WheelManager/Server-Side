package com.hackdead.wheelmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Column(name="password", nullable = false, length = 25)
    private String password;
    @Column(name = "email", nullable = false, length = 150)
    private String email;
    @Column(name = "name", nullable = false, length = 25)
    private String name;
    @Column(name = "lastname", nullable = false, length = 30)
    private String lastname;
    @Column(name = "image_url", nullable = false, length = 255)
    private String image_url;
    @Column(name="dni", nullable=false, length = 8)
    private String dni;
    @Column(name="gender", nullable = false, length = 20)
    private String gender;
    @Column(name="birth_day", nullable = false)
    private Date birth_day;
}

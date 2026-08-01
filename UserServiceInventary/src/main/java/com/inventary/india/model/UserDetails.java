package com.inventary.india.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;



@Data
@Entity
public class UserDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userName;
    private String email;
    private String mobile;
    private  String password;
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private Status status;
}

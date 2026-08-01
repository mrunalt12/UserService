package com.inventary.india.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class AdminDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adminId;
    private String adminName;
    private String adminEmail;
    private String adminMobile;
    private  String password;
    private String confirmPassword;
    @Enumerated(EnumType.STRING)
    private Status status;

}

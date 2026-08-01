package com.inventary.india.model.response;

import com.inventary.india.model.Status;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@Data
public class UserDetailsResponseDto implements Serializable {
    private String UserName;
    private String email;
    private String mobile;
    private  String password;
    private String confirmPassword;
    private Status status;
    private  String Description;

}

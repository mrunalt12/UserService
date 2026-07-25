package com.inventary.india.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UserDetailsRequestDto {
    private String userName;
    private String email;
    private String mobile;
    private  String password;
    private String confirmPassword;
}

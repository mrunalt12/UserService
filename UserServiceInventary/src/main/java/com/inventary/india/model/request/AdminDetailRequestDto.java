package com.inventary.india.model.request;

import lombok.Data;

@Data
public class AdminDetailRequestDto {
    private String adminName;
    private String adminEmail;
    private String adminMobile;
    private  String password;
    private String confirmPassword;

}

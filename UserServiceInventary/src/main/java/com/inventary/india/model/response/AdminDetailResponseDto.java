package com.inventary.india.model.response;

import com.inventary.india.model.Status;
import lombok.Data;

@Data
public class AdminDetailResponseDto {
    private String adminId;
    private String adminName;
    private String adminEmail;
    private String adminMobile;
    private Status status;
    private  String Description;

}

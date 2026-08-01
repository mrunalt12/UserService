package com.inventary.india.model.response;

import com.inventary.india.model.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@Data
public class UserDetailResponseDto implements Serializable {
    private String userId;
    private String UserName;
    private String email;
    private String mobile;
    private Status status;
    private  String Description;

}

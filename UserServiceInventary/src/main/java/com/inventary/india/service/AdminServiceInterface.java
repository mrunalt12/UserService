package com.inventary.india.service;

import com.inventary.india.model.request.AdminDetailRequestDto;
import com.inventary.india.model.request.UserDetailRequestDto;
import com.inventary.india.model.response.AdminDetailResponseDto;
import com.inventary.india.model.response.UserDetailResponseDto;

import java.util.List;

public interface AdminServiceInterface {
    AdminDetailResponseDto addAdminDetails(AdminDetailRequestDto adminDetailsRequest);
    
    AdminDetailResponseDto updateAdminDetails(Long userId, AdminDetailRequestDto adminDetailsRequest);

    AdminDetailResponseDto disableAdmin(Long adminId);

    AdminDetailResponseDto deleteAdmin(Long adminId);

    List<UserDetailResponseDto> findUserDetails(AdminDetailRequestDto adminRequest);
}

package com.inventary.india.service;

import com.inventary.india.model.request.UserDetailRequestDto;
import com.inventary.india.model.response.UserDetailResponseDto;

public interface UserServiceinterface {
    UserDetailResponseDto createUser(UserDetailRequestDto userDetails);

    UserDetailResponseDto updateUserDetails(Long userId, UserDetailRequestDto userDetails);

    UserDetailResponseDto disableUserDetails(Long userId);

    UserDetailResponseDto deleteUserDetails(Long userId);
}

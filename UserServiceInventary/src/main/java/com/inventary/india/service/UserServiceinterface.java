package com.inventary.india.service;

import com.inventary.india.model.UserDetails;
import com.inventary.india.model.request.UserDetailsRequestDto;
import com.inventary.india.model.response.UserDetailsResponseDto;

public interface UserServiceinterface {
    UserDetailsResponseDto createUser(UserDetailsRequestDto userDetails);

    UserDetailsResponseDto updateUserDetails(Long userId, UserDetailsRequestDto userDetails);

    UserDetailsResponseDto disableUserDetails(Long userId);

    UserDetailsResponseDto deleteUserDetails(Long userId);
}

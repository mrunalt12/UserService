package com.inventary.india.service;

import com.inventary.india.constants.Constant;
import com.inventary.india.mapper.UserDetailsMapper;
import com.inventary.india.model.Status;
import com.inventary.india.model.UserDetails;
import com.inventary.india.model.request.UserDetailRequestDto;
import com.inventary.india.model.response.UserDetailResponseDto;
import com.inventary.india.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceinterface{

    @Autowired
    private UserRepositoryInterface userRepositoryInterface;
    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetailResponseDto createUser(UserDetailRequestDto userDetailsRequestDto) {

//        requestdto to entity
        UserDetails userDetails= userDetailsMapper.toEntity(userDetailsRequestDto);
        userDetails.setStatus(Status.CREATED);

        UserDetails saveUserDetails  =userRepositoryInterface.save(userDetails);

//        entity to response dto
        UserDetailResponseDto userDetailsResponseDto = userDetailsMapper.toResponse(saveUserDetails);
        userDetailsResponseDto.setDescription(Constant.USER_CREATED);

        return userDetailsResponseDto;
    }

    @Override
    public UserDetailResponseDto updateUserDetails(Long userId, UserDetailRequestDto userDetailsRequestDto) {

        UserDetails userDetails= userRepositoryInterface.findById(userId)
                        .orElseThrow(()-> new RuntimeException("User with "+ userId+" Not found."));

        userDetails.setUserName(userDetailsRequestDto.getUserName());
        userDetails.setEmail(userDetailsRequestDto.getEmail());
        userDetails.setMobile(userDetailsRequestDto.getMobile());
        userDetails.setPassword(userDetailsRequestDto.getPassword());
        userDetails.setConfirmPassword(userDetailsRequestDto.getConfirmPassword());


        UserDetails saveUserDetails  =userRepositoryInterface.save(userDetails);

        //        entity to response dto
        UserDetailResponseDto userDetailsResponseDto = userDetailsMapper.toResponse(saveUserDetails);
        userDetailsResponseDto.setDescription(Constant.USER_UPDATED);

        return userDetailsResponseDto;
    }

    @Override
    public UserDetailResponseDto disableUserDetails(Long userId) {

        UserDetails userDetails= userRepositoryInterface.findById(userId)
                .orElseThrow(()-> new RuntimeException("User with "+ userId+" Not found."));
        userDetails.setStatus(Status.TEMPRARY_DELETED);
        UserDetails saveUserDetails=userRepositoryInterface.save(userDetails);

        //        entity to response dto
        UserDetailResponseDto userDetailsResponseDto = userDetailsMapper.toResponse(saveUserDetails);
        userDetailsResponseDto.setDescription(Constant.USER_DISABLE);

        return userDetailsResponseDto;
    }

    @Override
    public UserDetailResponseDto deleteUserDetails(Long userId) {
        UserDetails userDetails= userRepositoryInterface.findById(userId)
                .orElseThrow(()-> new RuntimeException("User with "+ userId+" Not found."));

         userRepositoryInterface.deleteById(userId);
        //        entity to response dto
        UserDetailResponseDto userDetailsResponseDto = userDetailsMapper.toResponse(userDetails);
        userDetailsResponseDto.setDescription(Constant.USER_DELETED);
        return userDetailsResponseDto;
    }


}

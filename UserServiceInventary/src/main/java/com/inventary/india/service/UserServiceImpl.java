package com.inventary.india.service;

import com.inventary.india.mapper.UserDetailsMapper;
import com.inventary.india.model.Status;
import com.inventary.india.model.UserDetails;
import com.inventary.india.model.request.UserDetailsRequestDto;
import com.inventary.india.model.response.UserDetailsResponseDto;
import com.inventary.india.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceinterface{

    private final String USER_CREATED="User is created Successfully";
    private final String USER_UPDATED="User is updated Successfully";
    private final String USER_DISABLE="User is disable temporarily";
    private final String USER_DELETED="User is deleted Success Fully";

    @Autowired
    private UserRepositoryInterface userRepositoryInterface;
    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetailsResponseDto createUser(UserDetailsRequestDto userDetailsRequestDto) {

//        requestdto to entity
        UserDetails userDetails= userDetailsMapper.toEntity(userDetailsRequestDto);
        userDetails.setStatus(Status.CREATED);

        UserDetails saveUserDetails  =userRepositoryInterface.save(userDetails);

//        entity to response dto
        UserDetailsResponseDto userDetailsResponseDto = userDetailsMapper.toResponse(saveUserDetails);
        userDetailsResponseDto.setDescription(USER_CREATED);

        return userDetailsResponseDto;
    }

    @Override
    public UserDetailsResponseDto updateUserDetails(Long userId, UserDetailsRequestDto userDetailsRequestDto) {

        UserDetails userDetails= userRepositoryInterface.findById(userId)
                        .orElseThrow(()-> new RuntimeException("User with "+ userId+" Not found."));

        userDetails.setUserName(userDetailsRequestDto.getUserName());
        userDetails.setEmail(userDetailsRequestDto.getEmail());
        userDetails.setMobile(userDetailsRequestDto.getMobile());
        userDetails.setPassword(userDetailsRequestDto.getPassword());
        userDetails.setConfirmPassword(userDetailsRequestDto.getConfirmPassword());


        UserDetails saveUserDetails  =userRepositoryInterface.save(userDetails);

        UserDetailsResponseDto userDetailsResponseDto = userDetailsMapper.toResponse(saveUserDetails);
        userDetailsResponseDto.setDescription(USER_UPDATED);

        return userDetailsResponseDto;
    }

    @Override
    public UserDetailsResponseDto disableUserDetails(Long userId) {

        UserDetails userDetails= userRepositoryInterface.findById(userId)
                .orElseThrow(()-> new RuntimeException("User with "+ userId+" Not found."));
        userDetails.setStatus(Status.DELETED);
        UserDetails saveUserDetails=userRepositoryInterface.save(userDetails);

        //        entity to response dto
        UserDetailsResponseDto userDetailsResponseDto = userDetailsMapper.toResponse(saveUserDetails);
        userDetailsResponseDto.setDescription(USER_DISABLE);

        return userDetailsResponseDto;
    }

    @Override
    public UserDetailsResponseDto deleteUserDetails(Long userId) {
        UserDetails userDetails= userRepositoryInterface.findById(userId)
                .orElseThrow(()-> new RuntimeException("User with "+ userId+" Not found."));

         userRepositoryInterface.deleteById(userId);
        //        entity to response dto
        UserDetailsResponseDto userDetailsResponseDto = userDetailsMapper.toResponse(userDetails);
        userDetailsResponseDto.setDescription(USER_DELETED);
        return userDetailsResponseDto;
    }


}

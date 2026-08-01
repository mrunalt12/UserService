package com.inventary.india.controller;

import com.inventary.india.model.request.UserDetailRequestDto;
import com.inventary.india.model.response.UserDetailResponseDto;
import com.inventary.india.service.UserServiceinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceinterface userServiceinterface;

    //create user
    @PostMapping("/createUser")
    public ResponseEntity<UserDetailResponseDto> createUserFirstTime(@RequestBody UserDetailRequestDto userDetailsRequestDto){
        UserDetailResponseDto userResponse =userServiceinterface.createUser(userDetailsRequestDto);
       return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }


    //update user
    @PutMapping("/updateUserDetails/{userId}")
    public ResponseEntity<UserDetailResponseDto> updateUserDetails(@PathVariable Long userId , @RequestBody UserDetailRequestDto userDetailsRequestDto)
    {
        UserDetailResponseDto userResponse = userServiceinterface.updateUserDetails(userId,userDetailsRequestDto);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    //disable user(soft delete)
    @PutMapping("/disableUserDetails/{userId}")
    public ResponseEntity<UserDetailResponseDto> disableUserDetails(@PathVariable Long userId )
    {
        UserDetailResponseDto userResponse = userServiceinterface.disableUserDetails(userId);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    //delete user(complete delete)
    @DeleteMapping ("/deleteUserDetails/{userId}")
    public ResponseEntity<UserDetailResponseDto>  deleteUserDetails(@PathVariable Long userId )
    {
        UserDetailResponseDto userResponse = userServiceinterface.deleteUserDetails(userId);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}


package com.inventary.india.controller;

import com.inventary.india.model.request.AdminDetailRequestDto;
import com.inventary.india.model.request.UserDetailRequestDto;
import com.inventary.india.model.response.AdminDetailResponseDto;

import com.inventary.india.model.response.UserDetailResponseDto;
import com.inventary.india.service.AdminServiceInterface;
import com.inventary.india.service.UserServiceinterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private AdminServiceInterface adminServiceInterface;

    @Autowired
    public AdminController(AdminServiceInterface adminServiceInterface) {
        this.adminServiceInterface = adminServiceInterface;
    }

    //create admin
    @PostMapping("/addAdmin")
    public ResponseEntity<AdminDetailResponseDto> createAdminFirstTime(@RequestBody AdminDetailRequestDto adminDetailsRequest){
        AdminDetailResponseDto adminDetailsResponse =adminServiceInterface.addAdminDetails(adminDetailsRequest);
        return new ResponseEntity<>(adminDetailsResponse, HttpStatus.CREATED);
    }

    //update Admin
    @PutMapping("/updateAdminDetails/{adminId}")
    public ResponseEntity<AdminDetailResponseDto> updateAdminDetails(@PathVariable Long adminId ,
                                                                     @RequestBody AdminDetailRequestDto adminDetailsRequest)
    {
        AdminDetailResponseDto adminDetailsResponse = adminServiceInterface.updateAdminDetails(adminId,adminDetailsRequest);
        return new ResponseEntity<>(adminDetailsResponse, HttpStatus.OK);
    }
    //disable user(soft delete)
    @PutMapping("/disableAdmin/{adminId}")
    public ResponseEntity<AdminDetailResponseDto> disableAdminDetails(@PathVariable Long adminId )
    {
        AdminDetailResponseDto adminResponse = adminServiceInterface.disableAdmin(adminId);
        return new ResponseEntity<>(adminResponse, HttpStatus.OK);
    }

    //delete user(complete delete)
    @DeleteMapping ("/deleteAdminDetails/{adminId}")
    public ResponseEntity<AdminDetailResponseDto>  deleteAdminDetails(@PathVariable Long adminId )
    {
        AdminDetailResponseDto adminResponse = adminServiceInterface.deleteAdmin(adminId);
        return new ResponseEntity<>(adminResponse, HttpStatus.OK);
    }

    // find user Detaills
    @GetMapping("/findUserDetails")
    public ResponseEntity<List<UserDetailResponseDto>> findUserDetails(@RequestBody AdminDetailRequestDto adminRequest)  {
        List<UserDetailResponseDto>  userResponseList = adminServiceInterface.findUserDetails(adminRequest);
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }




}

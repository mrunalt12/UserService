package com.inventary.india.service;

import com.inventary.india.constants.Constant;
import com.inventary.india.mapper.AdminDetailsMapper;
import com.inventary.india.mapper.UserDetailsMapper;
import com.inventary.india.model.AdminDetail;
import com.inventary.india.model.Status;
import com.inventary.india.model.UserDetails;
import com.inventary.india.model.request.AdminDetailRequestDto;
import com.inventary.india.model.response.AdminDetailResponseDto;
import com.inventary.india.model.response.UserDetailResponseDto;
import com.inventary.india.repository.AdminServiceRepository;
import com.inventary.india.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements  AdminServiceInterface{

    private AdminServiceRepository adminServiceRepository;
    private AdminDetailsMapper adminDetailsMapper;
    private  PasswordEncoder passwordEncoder;
    private UserRepositoryInterface userRepositoryInterface;
    private UserDetailsMapper userDetailsMapper;
    @Autowired
    public AdminServiceImpl(AdminServiceRepository adminServiceRepository, AdminDetailsMapper adminDetailsMapper,
                            PasswordEncoder passwordEncoder,
                            UserRepositoryInterface userRepositoryInterface,
                            UserDetailsMapper userDetailsMapper) {
        this.adminServiceRepository = adminServiceRepository;
        this.adminDetailsMapper = adminDetailsMapper;
        this.passwordEncoder=passwordEncoder;
        this.userRepositoryInterface=userRepositoryInterface;
        this.userDetailsMapper=userDetailsMapper;
    }

    @Override
    public AdminDetailResponseDto addAdminDetails(AdminDetailRequestDto adminDetailsRequest) {

        //        requestdto to entity
        AdminDetail adminDetail= adminDetailsMapper.toEntity(adminDetailsRequest);
        adminDetail.setPassword(encriptPassword(adminDetailsRequest.getPassword()));
        adminDetail.setConfirmPassword(encriptPassword(adminDetailsRequest.getConfirmPassword()));
        adminDetail.setStatus(Status.CREATED);
        AdminDetail  saveAdminDetail=adminServiceRepository.save(adminDetail);

        //        entity to response dto
         AdminDetailResponseDto adminDetailsResponse =adminDetailsMapper.toResponse(saveAdminDetail);
        adminDetailsResponse.setDescription(Constant.ADMIN_CREATED);
        return adminDetailsResponse;
    }

    @Override
    public AdminDetailResponseDto updateAdminDetails(Long adminId, AdminDetailRequestDto adminDetailsRequest) {

        // find admin
        AdminDetail adminDetails=adminServiceRepository.findById(adminId).orElseThrow(
                ()-> new RuntimeException("Admin with "+ adminId+" Not found."));

        adminDetails.setAdminName(adminDetailsRequest.getAdminName());
        adminDetails.setAdminEmail(adminDetailsRequest.getAdminEmail());
        adminDetails.setAdminMobile(adminDetailsRequest.getAdminMobile());
        adminDetails.setPassword(encriptPassword(adminDetailsRequest.getPassword()));
        adminDetails.setConfirmPassword(encriptPassword(adminDetailsRequest.getConfirmPassword()));

        AdminDetail saveAdminDetails=adminServiceRepository.save(adminDetails);

        AdminDetailResponseDto adminResponse= adminDetailsMapper.toResponse(saveAdminDetails);
        adminResponse.setDescription(Constant.ADMIN__UPDATED);

        return adminResponse;
    }

    @Override
    public AdminDetailResponseDto disableAdmin(Long adminId) {
        // find admin
        AdminDetail adminDetails=adminServiceRepository.findById(adminId).orElseThrow(
                ()-> new RuntimeException("Admin with "+ adminId+" Not found."));
        adminDetails.setStatus(Status.TEMPRARY_DELETED);

        AdminDetail saveAdminDetail= adminServiceRepository.save(adminDetails);
        AdminDetailResponseDto adminResponse= adminDetailsMapper.toResponse(saveAdminDetail);
        adminResponse.setDescription(Constant.ADMIN__DISABLE);
        return adminResponse;
    }

    @Override
    public AdminDetailResponseDto deleteAdmin(Long adminId) {
        // find admin
        AdminDetail adminDetails=adminServiceRepository.findById(adminId).orElseThrow(
                ()-> new RuntimeException("Admin with "+ adminId+" Not found."));

         adminServiceRepository.save(adminDetails);
        AdminDetailResponseDto adminResponse= adminDetailsMapper.toResponse(adminDetails);
        adminResponse.setDescription(Constant.ADMIN__DELETED);
        return adminResponse;
    }

    @Override
    public List<UserDetailResponseDto> findUserDetails(AdminDetailRequestDto adminRequest) {

        List<AdminDetail> adminDetailsList =adminServiceRepository.findAll();

       Optional<AdminDetail> adminFound= adminDetailsList.stream().filter(
                adminDetail -> adminDetail.getAdminEmail().equals(adminRequest.getAdminEmail()) &&
                        adminDetail.getAdminMobile().equals(adminRequest.getAdminMobile())  &&
                        passwordEncoder.matches(adminRequest.getPassword(), adminDetail.getPassword())
        ).findFirst();

       if(adminFound.isPresent()){
           List<UserDetails> userDetails= userRepositoryInterface.findAll();
           List<UserDetailResponseDto> userResponseList= userDetailsMapper.toResponseList(userDetails);
           return userResponseList;
       }
         else{
              throw new RuntimeException("Admin with "+ adminRequest.getAdminEmail()+" Not found.");
         }
    }


    // password encoder
    private String encriptPassword(String password){
        String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
    }
}

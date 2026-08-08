package com.inventary.india.mapper;

import com.inventary.india.model.UserDetails;
import com.inventary.india.model.request.UserDetailRequestDto;
import com.inventary.india.model.response.UserDetailResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userName" ,source = "userName")
    @Mapping(target = "email" ,source = "email")
    @Mapping(target = "mobile",source = "mobile")
    @Mapping(target = "password",source = "password")
    @Mapping(target="confirmPassword",source = "confirmPassword")
    @Mapping(target = "userCity",source = "userCity")
    @Mapping(target="userAddress",source = "userAddress")
    UserDetails toEntity(UserDetailRequestDto userDetailsRequestDto);


    @Mapping(target = "userName" ,source = "userName")
    @Mapping(target = "email" ,source = "email")
    @Mapping(target = "mobile",source = "mobile")
    @Mapping(target = "userAddress",source = "userAddress")
    @Mapping(target = "userCity", source = "userCity")
    @Mapping(target = "status", source = "status")
    UserDetailResponseDto toResponse(UserDetails userDetails);

    List<UserDetailResponseDto> toResponseList(List<UserDetails> userDetails);
}

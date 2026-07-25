package com.inventary.india.mapper;

import com.inventary.india.model.UserDetails;
import com.inventary.india.model.request.UserDetailsRequestDto;
import com.inventary.india.model.response.UserDetailsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userName" ,source = "userName")
    @Mapping(target = "email" ,source = "email")
    @Mapping(target = "mobile",source = "mobile")
    @Mapping(target = "password",source = "password")
    @Mapping(target="confirmPassword",source = "confirmPassword")
    UserDetails toEntity(UserDetailsRequestDto userDetailsRequestDto);


    @Mapping(target = "userName" ,source = "userName")
    @Mapping(target = "email" ,source = "email")
    @Mapping(target = "mobile",source = "mobile")
    @Mapping(target = "password",source = "password")
    @Mapping(target="confirmPassword",source = "confirmPassword")
    @Mapping(target = "status", source = "status")
    UserDetailsResponseDto toResponse(UserDetails userDetails);
}

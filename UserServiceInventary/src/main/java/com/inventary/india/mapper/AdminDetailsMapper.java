package com.inventary.india.mapper;

import com.inventary.india.model.AdminDetail;
import com.inventary.india.model.request.AdminDetailRequestDto;
import com.inventary.india.model.response.AdminDetailResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminDetailsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "adminName" ,source = "adminName")
    @Mapping(target = "adminEmail" ,source = "adminEmail")
    @Mapping(target = "adminMobile",source = "adminMobile")
    AdminDetail toEntity(AdminDetailRequestDto adminDetailsRequest);


    @Mapping(target = "adminName" ,source = "adminName")
    @Mapping(target = "adminEmail" ,source = "adminEmail")
    @Mapping(target = "adminMobile",source = "adminMobile")
    @Mapping(target = "status", source = "status")
    AdminDetailResponseDto toResponse(AdminDetail adminDetail);
}

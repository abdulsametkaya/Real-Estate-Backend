package com.realestate.dto.mapper;

import com.realestate.domain.User;
import com.realestate.dto.request.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User registerRequstToUser(RegisterRequest registerRequest);

}

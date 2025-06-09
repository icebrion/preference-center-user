package com.vw.apirest.api.mapper;

import com.vw.apirest.api.dto.UserRequest;
import com.vw.apirest.api.dto.UserResponse;
import com.vw.domain.aggregate.User;
import com.vw.domain.entity.Consent;
import com.vw.user.create.CreateUserRequest;
import com.vw.user.create.CreateUserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    CreateUserRequest asCreateUserRequest(UserRequest dto);

    List<Consent> asConsent(List<com.vw.apirest.api.dto.Consent> dto);

    UserResponse asUserResponse(User dto);
}

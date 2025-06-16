package com.vw.apirest.mapper;

import com.vw.api.dto.UserRequest;
import com.vw.api.dto.UserResponse;
import com.vw.domain.aggregate.User;
import com.vw.domain.entity.Consent;
import com.vw.user.upsert.UpsertUserRequest;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {
    UpsertUserRequest asCreateUserRequest(UserRequest dto);

    UserResponse asUserResponse(User user);

    List<Consent> asConsentDomainList(List<com.vw.api.dto.Consent> dto);

    List<UserResponse> asUserResponseList(Set<User> users);
}

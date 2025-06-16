package com.vw.apirest.mapper;

import com.vw.api.dto.Consent;
import com.vw.api.dto.UserRequest;
import com.vw.api.dto.UserResponse;
import com.vw.apirest.controller.UserApiController;
import com.vw.common.Mediator;
import com.vw.domain.aggregate.User;
import com.vw.domain.enums.ConsentId;
import com.vw.user.delete.DeleteUserRequest;
import com.vw.user.delete.DeleteUserResponse;
import com.vw.user.get.GetUserResponse;
import com.vw.user.get.GetUsersRequest;
import com.vw.user.upsert.UpsertUserRequest;
import com.vw.user.upsert.UpsertUserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserMapperUnitTest {

    @InjectMocks
    private UserMapperImpl userMapper;

    @DisplayName("Given user request When asCreateUserRequest Then return create user request")
    @Test
    void asCreateUserRequest_success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("email");
        Consent consent = new Consent();
        consent.setId("SMS_NOTIFICATIONS");
        consent.setEnabled(true);
        userRequest.consents(List.of(consent));

        UpsertUserRequest createUserRequest = this.userMapper.asCreateUserRequest(userRequest);

        Assertions.assertEquals("email", createUserRequest.getEmail());
        Assertions.assertEquals(ConsentId.SMS_NOTIFICATIONS, createUserRequest.getConsents().get(0).getId());
        Assertions.assertTrue(createUserRequest.getConsents().get(0).isEnabled());
    }
}
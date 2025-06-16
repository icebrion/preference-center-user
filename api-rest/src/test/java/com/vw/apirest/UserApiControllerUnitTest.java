package com.vw.apirest;

import com.vw.api.dto.UserRequest;
import com.vw.api.dto.UserResponse;
import com.vw.apirest.api.mapper.UserMapper;
import com.vw.common.Mediator;
import com.vw.domain.aggregate.User;
import com.vw.user.upsert.UpsertUserRequest;
import com.vw.user.upsert.UpsertUserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserApiControllerUnitTest {

    @InjectMocks
    private UserApiController userApiController;

    @Mock
    private UserMapper userMapper;

    @Mock
    private Mediator mediator;

    @DisplayName("Given user request When upsert user Then return created user")
    @Test
    void upsertUser_success() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        UserResponse userResponse = Mockito.mock(UserResponse.class);
        UpsertUserRequest upsertUserRequest = Mockito.mock(UpsertUserRequest.class);
        User user = Mockito.mock(User.class);
        UpsertUserResponse upsertUserResponse = Mockito.mock(UpsertUserResponse.class);
        Mockito.when(upsertUserResponse.getUser()).thenReturn(user);

        Mockito.when(this.userMapper.asCreateUserRequest(userRequest)).thenReturn(upsertUserRequest);
        Mockito.when(this.mediator.handle(upsertUserRequest)).thenReturn(upsertUserResponse);
        Mockito.when(this.userMapper.asUserResponse(user)).thenReturn(userResponse);

        ResponseEntity<UserResponse> responseEntity = this.userApiController.upsertUser(userRequest);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(userResponse, responseEntity.getBody());
    }
}

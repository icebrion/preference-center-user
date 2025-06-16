package com.vw.apirest;

import com.vw.api.dto.UserRequest;
import com.vw.api.dto.UserResponse;
import com.vw.apirest.api.mapper.UserMapper;
import com.vw.common.Mediator;
import com.vw.domain.aggregate.User;
import com.vw.user.create.CreateUserRequest;
import com.vw.user.create.CreateUserResponse;
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
        CreateUserRequest createUserRequest = Mockito.mock(CreateUserRequest.class);
        User user = Mockito.mock(User.class);
        CreateUserResponse createUserResponse = Mockito.mock(CreateUserResponse.class);
        Mockito.when(createUserResponse.getUser()).thenReturn(user);

        Mockito.when(this.userMapper.asCreateUserRequest(userRequest)).thenReturn(createUserRequest);
        Mockito.when(this.mediator.handle(createUserRequest)).thenReturn(createUserResponse);
        Mockito.when(this.userMapper.asUserResponse(user)).thenReturn(userResponse);

        ResponseEntity<UserResponse> responseEntity = this.userApiController.upsertUser(userRequest);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(userResponse, responseEntity.getBody());
    }
}

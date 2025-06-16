package com.vw.apirest.controller;

import com.vw.api.dto.UserRequest;
import com.vw.api.dto.UserResponse;
import com.vw.apirest.mapper.UserMapper;
import com.vw.common.Mediator;
import com.vw.domain.aggregate.User;
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
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

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

    @DisplayName("Given user request When upsert user Then return invalid email")
    @Test
    void upsertUser_invalid() {
        UserRequest userRequest = Mockito.mock(UserRequest.class);
        UpsertUserRequest upsertUserRequest = Mockito.mock(UpsertUserRequest.class);
        UpsertUserResponse upsertUserResponse = Mockito.mock(UpsertUserResponse.class);

        Mockito.when(this.userMapper.asCreateUserRequest(userRequest)).thenReturn(upsertUserRequest);
        Mockito.when(this.mediator.handle(upsertUserRequest)).thenReturn(upsertUserResponse);

        ResponseEntity<UserResponse> responseEntity = this.userApiController.upsertUser(userRequest);

        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
    }

    @DisplayName("When get all users Then return all users")
    @Test
    void getAllUsers_success() {
        UserResponse userResponse = Mockito.mock(UserResponse.class);
        User user = Mockito.mock(User.class);
        GetUserResponse getUserResponse = Mockito.mock(GetUserResponse.class);
        Mockito.when(getUserResponse.getUsers()).thenReturn(Set.of(user));

        Mockito.when(this.mediator.handle(ArgumentMatchers.any(GetUsersRequest.class))).thenReturn(getUserResponse);
        Mockito.when(this.userMapper.asUserResponseList(ArgumentMatchers.anySet())).thenReturn(List.of(userResponse));

        ResponseEntity<List<UserResponse>> responseEntity = this.userApiController.getAllUsers();

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(List.of(userResponse), responseEntity.getBody());
    }

    @DisplayName("Given user id When get user Then return user")
    @Test
    void getUser_success() {
        UserResponse userResponse = Mockito.mock(UserResponse.class);
        User user = Mockito.mock(User.class);
        GetUserResponse getUserResponse = Mockito.mock(GetUserResponse.class);
        Mockito.when(getUserResponse.getUsers()).thenReturn(Set.of(user));

        Mockito.when(this.mediator.handle(ArgumentMatchers.any(GetUsersRequest.class))).thenReturn(getUserResponse);
        Mockito.when(this.userMapper.asUserResponseList(ArgumentMatchers.anySet())).thenReturn(List.of(userResponse));

        ResponseEntity<UserResponse> responseEntity = this.userApiController.getUser("UUID");

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(userResponse, responseEntity.getBody());
    }

    @DisplayName("Given user id When get user Then not found")
    @Test
    void getUser_notFound() {
        GetUserResponse getUserResponse = Mockito.mock(GetUserResponse.class);

        Mockito.when(this.mediator.handle(ArgumentMatchers.any(GetUsersRequest.class))).thenReturn(getUserResponse);

        ResponseEntity<UserResponse> responseEntity = this.userApiController.getUser("UUID");

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @DisplayName("Given user id When delete user Then return no content")
    @Test
    void deleteUser_success() {
        DeleteUserResponse deleteUserResponse = Mockito.mock(DeleteUserResponse.class);

        Mockito.when(this.mediator.handle(ArgumentMatchers.any(DeleteUserRequest.class))).thenReturn(deleteUserResponse);

        ResponseEntity<Void> responseEntity = this.userApiController.deleteUser("UUID");

        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}

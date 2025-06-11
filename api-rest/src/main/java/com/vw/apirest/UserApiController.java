package com.vw.apirest;


import com.vw.apirest.api.controller.UsersApi;
import com.vw.apirest.api.dto.UserRequest;
import com.vw.apirest.api.dto.UserResponse;
import com.vw.apirest.api.mapper.UserMapper;
import com.vw.common.Mediator;
import com.vw.user.create.CreateUserRequest;
import com.vw.user.create.CreateUserResponse;
import com.vw.user.delete.DeleteUserRequest;
import com.vw.user.get.GetUserResponse;
import com.vw.user.get.GetUsersRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserApiController implements UsersApi {

    private final Mediator mediator;

    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserResponse> upsertUser(UserRequest userRequest) {
        CreateUserRequest request = this.userMapper.asCreateUserRequest(userRequest);

        CreateUserResponse response = mediator.handle(request);
        if (response.getUser() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.userMapper.asUserResponse(response.getUser()));
        }

        return ResponseEntity.internalServerError().build();
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        GetUserResponse response = this.mediator.handle(GetUsersRequest.builder().build());

        List<UserResponse> userResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(response.getUsers())) {
             userResponseList.addAll(this.userMapper.asUserResponseList(response.getUsers()));
        }

        return ResponseEntity.ok(userResponseList);
    }

    @Override
    public ResponseEntity<UserResponse> getUser(String userId) {
        GetUsersRequest request = GetUsersRequest.builder()
                .userId(userId)
                .build();
        GetUserResponse response = this.mediator.handle(request);

        if (!CollectionUtils.isEmpty(response.getUsers())) {
            List<UserResponse> userResponseList = this.userMapper.asUserResponseList(response.getUsers());
            return ResponseEntity.ok(userResponseList.get(0));
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userId) {
        DeleteUserRequest request = DeleteUserRequest.builder()
                .userId(userId)
                .build();
        this.mediator.handle(request);

        return ResponseEntity.noContent().build();
    }
}

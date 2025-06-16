package com.vw.apirest.controller;


import com.vw.api.controller.UsersApi;
import com.vw.api.dto.UserRequest;
import com.vw.api.dto.UserResponse;
import com.vw.apirest.mapper.UserMapper;
import com.vw.common.Mediator;
import com.vw.user.upsert.UpsertUserRequest;
import com.vw.user.upsert.UpsertUserResponse;
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
        try {
            UpsertUserRequest request = this.userMapper.asCreateUserRequest(userRequest);
            UpsertUserResponse response = mediator.handle(request);

            if (response.getUser() != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(this.userMapper.asUserResponse(response.getUser()));
            }

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        try {
        GetUserResponse response = this.mediator.handle(GetUsersRequest.builder().build());

        List<UserResponse> userResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(response.getUsers())) {
             userResponseList.addAll(this.userMapper.asUserResponseList(response.getUsers()));
        }

        return ResponseEntity.ok(userResponseList);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<UserResponse> getUser(String userId) {
        try {
        GetUsersRequest request = GetUsersRequest.builder()
                .userId(userId)
                .build();
        GetUserResponse response = this.mediator.handle(request);

        if (!CollectionUtils.isEmpty(response.getUsers())) {
            List<UserResponse> userResponseList = this.userMapper.asUserResponseList(response.getUsers());
            return ResponseEntity.ok(userResponseList.get(0));
        }

        return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userId) {
        try {
        DeleteUserRequest request = DeleteUserRequest.builder()
                .userId(userId)
                .build();
        this.mediator.handle(request);

        return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

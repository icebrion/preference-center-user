package com.vw.apirest;


import com.vw.apirest.api.controller.UsersApi;
import com.vw.apirest.api.dto.UserRequest;
import com.vw.apirest.api.dto.UserResponse;
import com.vw.apirest.api.mapper.UserMapper;
import com.vw.common.Mediator;
import com.vw.user.create.CreateUserRequest;
import com.vw.user.create.CreateUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserApiController implements UsersApi {

    private final Mediator mediator;

    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserResponse> upsertUser(UserRequest userRequest) {
        CreateUserRequest request = this.userMapper.asCreateUserRequest(userRequest);
        CreateUserResponse response = mediator.handle(request);
        UserResponse userResponse = this.userMapper.asUserResponse(response.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}

package com.vw.user.get;

import com.vw.common.Handler;
import com.vw.domain.aggregate.User;
import com.vw.domain.repository.UserRepository;
import com.vw.user.create.CreateUserRequest;
import com.vw.user.create.CreateUserResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
public class GetUsersHandler implements Handler<GetUsersRequest, GetUserResponse> {

    private final UserRepository userRepository;

    @Override
    public GetUserResponse handle(GetUsersRequest request) {
        Set<User> usersResponse = new HashSet<>();

        if (request.getUserId() != null) {
            Optional<User> userById = this.userRepository.findUserById(request.getUserId());
            userById.ifPresent(usersResponse::add);
        } else {
            usersResponse = this.userRepository.findAllUsers();
        }

        return GetUserResponse.builder().users(usersResponse).build();
    }
}


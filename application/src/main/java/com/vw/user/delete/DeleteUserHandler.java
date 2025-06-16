package com.vw.user.delete;

import com.vw.common.Handler;
import com.vw.domain.aggregate.User;
import com.vw.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DeleteUserHandler implements Handler<DeleteUserRequest, DeleteUserResponse> {

    private final UserRepository userRepository;

    @Override
    public DeleteUserResponse handle(DeleteUserRequest request) {
        Optional<User> userById = this.userRepository.findUserById(request.getUserId());
        userById.ifPresent(this.userRepository::delete);

        return DeleteUserResponse.builder().build();
    }
}


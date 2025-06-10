package com.vw.user.create;

import com.vw.common.Handler;
import com.vw.domain.aggregate.User;
import com.vw.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateUserHandler implements Handler<CreateUserRequest, CreateUserResponse> {

    private final UserRepository userRepository;

    @Override
    public CreateUserResponse handle(CreateUserRequest request) {
        User userToSave;

        Optional<User> userByEmail = this.userRepository.findUserByEmail(request.getEmail());
        if (userByEmail.isPresent()) {
            userToSave = userByEmail.get().addConsents(request.getConsents());
        } else {
            userToSave = User.createUser(request.getEmail(), request.getConsents());
        }

        this.userRepository.save(userToSave);

        // Producer sends the event ConsentUpdateEvent
        
        return CreateUserResponse.builder()
                .user(userToSave)
                .build();
    }
}


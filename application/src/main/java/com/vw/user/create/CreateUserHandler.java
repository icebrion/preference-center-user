package com.vw.user.create;

import com.vw.common.DomainEventDispatcher;
import com.vw.common.Handler;
import com.vw.domain.aggregate.User;
import com.vw.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CreateUserHandler implements Handler<CreateUserRequest, CreateUserResponse> {

    private final UserRepository userRepository;

    private final DomainEventDispatcher domainEventDispatcher;

    @Override
    @Transactional
    public CreateUserResponse handle(CreateUserRequest request) {
        User userToSave;

        Optional<User> userByEmail = this.userRepository.findUserByEmail(request.getEmail());
        if (userByEmail.isPresent()) {
            userToSave = userByEmail.get().addConsents(request.getConsents());
        } else {
            userToSave = User.createUser(request.getEmail(), request.getConsents());
        }

        this.userRepository.save(userToSave);

        domainEventDispatcher.dispatch(userToSave.getDomainEvents());
        userToSave.clearDomainEvents();

        return CreateUserResponse.builder()
                .user(userToSave)
                .build();
    }
}


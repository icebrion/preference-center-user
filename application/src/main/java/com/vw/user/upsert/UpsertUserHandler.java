package com.vw.user.upsert;

import com.vw.common.DomainEventDispatcher;
import com.vw.common.Handler;
import com.vw.domain.aggregate.User;
import com.vw.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UpsertUserHandler implements Handler<UpsertUserRequest, UpsertUserResponse> {

    private final UserRepository userRepository;

    private final DomainEventDispatcher domainEventDispatcher;

    @Override
    @Transactional
    public UpsertUserResponse handle(UpsertUserRequest request) {
        User userToSave;

        Optional<User> userByEmail = this.userRepository.findUserByEmail(request.getEmail());
        if (userByEmail.isPresent()) {
            userToSave = userByEmail.get().addConsents(request.getConsents());
        } else if (this.isValidEmail()) {
            userToSave = User.createUser(request.getEmail(), request.getConsents());
        } else {
            return UpsertUserResponse.builder()
                    .build();
        }

        this.userRepository.save(userToSave);

        domainEventDispatcher.dispatch(userToSave.getDomainEvents());
        userToSave.clearDomainEvents();

        return UpsertUserResponse.builder()
                .user(userToSave)
                .build();
    }

    private boolean isValidEmail() {
        // TODO validations
        return true;
    }
}


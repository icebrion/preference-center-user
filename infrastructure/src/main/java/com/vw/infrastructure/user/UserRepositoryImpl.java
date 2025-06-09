package com.vw.infrastructure.user;

import com.vw.domain.aggregate.User;
import com.vw.domain.repository.UserRepository;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findUserByEmail(String email) {
        // DB query
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        // DB query
        return null;
    }
}

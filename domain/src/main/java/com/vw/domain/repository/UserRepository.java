package com.vw.domain.repository;

import com.vw.domain.aggregate.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByEmail(String email);

    User save(User user);
}

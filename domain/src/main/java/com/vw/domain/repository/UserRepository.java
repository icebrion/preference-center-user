package com.vw.domain.repository;

import com.vw.domain.aggregate.User;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(String id);

    Set<User> findAllUsers();

    User save(User user);

    void delete(User user);
}

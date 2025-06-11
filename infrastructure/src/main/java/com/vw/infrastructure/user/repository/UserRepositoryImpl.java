package com.vw.infrastructure.user.repository;

import com.vw.domain.aggregate.User;
import com.vw.domain.repository.UserRepository;
import com.vw.infrastructure.user.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaRepository;
    private final UserEntityMapper mapper;

    @Override
    public Optional<User> findUserByEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::asDomain);
    }

    @Override
    public Optional<User> findUserById(String id) {
        return jpaRepository.findById(id).map(mapper::asDomain);
    }

    @Override
    public Set<User> findAllUsers() {
        return jpaRepository.findAll().stream()
                .map(mapper::asDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public User save(User user) {
        return mapper.asDomain(jpaRepository.save(mapper.asEntity(user)));
    }

    @Override
    public void delete(User user) {
        jpaRepository.delete(mapper.asEntity(user));
    }
}

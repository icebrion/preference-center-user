package com.vw.infrastructure.user.mapper;

import com.vw.domain.aggregate.User;
import com.vw.infrastructure.user.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface UserEntityMapper {
    UserEntity asEntity(User domain);

    User asDomain(UserEntity entity);

    Set<User> asDomainSet(Set<UserEntity> entities);
}

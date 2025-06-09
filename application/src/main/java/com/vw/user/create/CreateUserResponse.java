package com.vw.user.create;

import com.vw.domain.aggregate.User;
import com.vw.domain.entity.Consent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CreateUserResponse {
    private User user;
}

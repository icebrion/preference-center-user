package com.vw.user.create;

import com.vw.common.Request;
import com.vw.domain.entity.Consent;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CreateUserRequest implements Request<CreateUserResponse> {
    private String email;
    private List<Consent> consents;
}


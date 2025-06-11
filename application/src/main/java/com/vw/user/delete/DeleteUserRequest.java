package com.vw.user.delete;

import com.vw.common.Request;
import com.vw.domain.entity.Consent;
import com.vw.user.create.CreateUserResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DeleteUserRequest implements Request<DeleteUserResponse> {
    private String userId;
}


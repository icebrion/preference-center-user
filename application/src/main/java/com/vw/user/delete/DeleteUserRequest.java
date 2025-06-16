package com.vw.user.delete;

import com.vw.common.Request;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteUserRequest implements Request<DeleteUserResponse> {
    private String userId;
}


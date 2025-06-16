package com.vw.user.get;

import com.vw.common.Request;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUsersRequest implements Request<GetUserResponse> {
    private String userId;
}


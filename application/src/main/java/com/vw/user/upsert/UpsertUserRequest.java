package com.vw.user.upsert;

import com.vw.common.Request;
import com.vw.domain.entity.Consent;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UpsertUserRequest implements Request<UpsertUserResponse> {
    private String email;
    private List<Consent> consents;
}


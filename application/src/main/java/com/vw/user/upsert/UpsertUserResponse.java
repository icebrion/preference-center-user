package com.vw.user.upsert;

import com.vw.domain.aggregate.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UpsertUserResponse {
    private User user;
}

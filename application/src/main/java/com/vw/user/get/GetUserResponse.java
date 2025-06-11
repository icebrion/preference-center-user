package com.vw.user.get;

import com.vw.domain.aggregate.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetUserResponse {
    private Set<User> users;
}

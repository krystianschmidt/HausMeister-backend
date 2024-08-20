package com.example.spuelmaschine.adapters.user;

import com.example.spuelmaschine.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserToUserResourceMapper implements Function<User, UserResource> {

    @Override
    public UserResource apply(final User user) {
        return map(user);
    }

    private UserResource map(final User user) {
        return UserResource.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

}

package com.example.hausmeister.adapters.user;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserResource {
    private final UUID id;
    private final String  username, name;
}

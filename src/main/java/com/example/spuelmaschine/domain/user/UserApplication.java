package com.example.spuelmaschine.domain.user;


import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.YearMonth;

public interface UserApplication extends UserDetailsService {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    void createUser(User registerUser);

    User getUser();

    void save(User user);

}

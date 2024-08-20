package com.example.spuelmaschine.plugins.hibernate.user;


import com.example.spuelmaschine.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
}

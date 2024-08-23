package com.example.hausmeister.plugins.hibernate.user;


import com.example.hausmeister.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
}

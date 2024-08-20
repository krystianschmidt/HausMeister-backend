package com.example.spuelmaschine.plugins.hibernate.user;


import com.example.spuelmaschine.domain.user.User;
import com.example.spuelmaschine.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryBridge implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;
    @Override
    public User findByUsername(String username) {
        return springDataUserRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return springDataUserRepository.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        springDataUserRepository.save(user);
    }


}

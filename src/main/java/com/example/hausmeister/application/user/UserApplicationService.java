package com.example.hausmeister.application.user;


import com.example.hausmeister.domain.user.User;
import com.example.hausmeister.domain.user.UserApplication;
import com.example.hausmeister.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements UserApplication {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void createUser(User registerUser) {
        if(userRepository.existsByUsername(registerUser.getUsername())){
            throw new RuntimeException("Username already exists!");
        }

        String encryptedPassword = passwordEncoder.encode(registerUser.getPassword());
        registerUser.setPassword(encryptedPassword);

        userRepository.save(registerUser);
    }

    @Override
    public User getUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername());
    }


    @Override
    public void save(User user) {
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }


}

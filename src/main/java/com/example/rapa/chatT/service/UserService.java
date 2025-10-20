//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.rapa.chatT.service;

import com.example.rapa.chatT.model.User;
import com.example.rapa.chatT.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.userRepository = repo;
        this.passwordEncoder = encoder;
    }

    public User registerUser(String username, String rawPassword, String nickname) {
        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(this.passwordEncoder.encode(rawPassword));
            user.setNickname(nickname);
            return (User)this.userRepository.save(user);
        }
    }

    public boolean authenticate(String username, String rawPassword) {
        return (Boolean)this.userRepository.findByUsername(username).map((user) -> this.passwordEncoder.matches(rawPassword, user.getPassword())).orElse(false);
    }
}

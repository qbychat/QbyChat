package org.cubewhy.qbychat.services.impl;

import jakarta.annotation.Resource;
import org.cubewhy.qbychat.entity.User;
import org.cubewhy.qbychat.repository.UserRepository;
import org.cubewhy.qbychat.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    PasswordEncoder passwordEncoder;

    public User findOneUserByNameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsername(usernameOrEmail).orElseGet(() -> userRepository.findByEmail(usernameOrEmail).orElse(null));
    }

    public User createUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}

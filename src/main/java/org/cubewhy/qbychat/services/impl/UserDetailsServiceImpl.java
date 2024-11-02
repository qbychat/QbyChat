package org.cubewhy.qbychat.services.impl;

import jakarta.annotation.Resource;
import org.cubewhy.qbychat.entity.User;
import org.cubewhy.qbychat.entity.UserDetailsImpl;
import org.cubewhy.qbychat.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findOneUserByNameOrEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return UserDetailsImpl.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}

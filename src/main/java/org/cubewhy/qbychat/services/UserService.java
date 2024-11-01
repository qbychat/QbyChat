package org.cubewhy.qbychat.services;

import org.cubewhy.qbychat.entity.User;

public interface UserService {
    User findOneUserByNameOrEmail(String usernameOrEmail);

    User createUser(String username, String email, String password);
}

package org.cubewhy.qbychat.services;

import org.cubewhy.qbychat.entity.dto.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
}

package org.cubewhy.qbychat.controller;

import jakarta.annotation.Resource;
import org.cubewhy.qbychat.entity.RestBean;
import org.cubewhy.qbychat.entity.dto.LoginDTO;
import org.cubewhy.qbychat.entity.vo.JWTAuthVO;
import org.cubewhy.qbychat.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/user")
public class UserController {

    @Resource
    AuthService authService;

    @PostMapping("/login")
    public RestBean<JWTAuthVO> login(@RequestBody LoginDTO loginDTO){
        String token = authService.login(loginDTO);

        JWTAuthVO jwtAuthVO = new JWTAuthVO();
        jwtAuthVO.setToken(token);

        return RestBean.success(jwtAuthVO);
    }

}

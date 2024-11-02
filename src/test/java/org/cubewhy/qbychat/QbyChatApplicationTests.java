package org.cubewhy.qbychat;

import org.cubewhy.qbychat.entity.ChannelPermission;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
class QbyChatApplicationTests {

    @Test
    void contextLoads() {
        Set<ChannelPermission> permissions = ChannelPermission.calculatePermissions(ChannelPermission.MEMBER_DEFAULT);
        System.out.println(permissions);
    }

}

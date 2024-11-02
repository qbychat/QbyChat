package org.cubewhy.qbychat.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class ChannelMember {
    @Id
    private String id;

    @DBRef
    private User user;
    @DBRef
    private Channel channel;

    private String nickname = user.getNickname();
    private String title = "";
    private List<ChannelPermission> permissions = channel.getDefaultPermissions();

    private boolean banned = false;
    private boolean owner = false; // if this is true, all permissions will be grant by default.
}

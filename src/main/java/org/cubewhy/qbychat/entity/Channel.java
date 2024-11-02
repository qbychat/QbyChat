package org.cubewhy.qbychat.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Channel {
    @Id
    private String id;

    private String name;
    private String description; // the description. markdown

    private Type type;

    // if type set to NOTICE, the SEND_MESSAGE Permission will be disabled for members.
    // if type set to PRIVATE_MESSAGE, nothing will be working here.
    private List<ChannelPermission> defaultPermissions = List.of(ChannelPermission.MEMBER_DEFAULT);

    static enum Type {
        GROUP,
        PRIVATE_MESSAGE,
        NOTICE
    }
}

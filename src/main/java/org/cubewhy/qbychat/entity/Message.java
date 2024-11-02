package org.cubewhy.qbychat.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Message {
    @Id
    private String id;

    @DBRef
    private User sender;
    @DBRef
    private User redirect = null;
    @DBRef
    private Channel channel;

    @DBRef
    private Message reply;

    private boolean pinned;
    private String preview; // preview in notifications
    private String content; // markdown formatted

    @DBRef
    private List<Media> media;
}

package org.cubewhy.qbychat.entity;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

@Getter
public enum ChannelPermission {
    SEND_TEXT_MESSAGE, // 发送文本信息
    SEND_PHOTOS_MESSAGE, // 发送图片
    SEND_VIDEOS_MESSAGE, // 发送视频
    SEND_MUSIC_MESSAGE, // 发送音乐
    SEND_STICKERS_MESSAGE, // 发送贴纸(表情包)
    SEND_FILES_MESSAGE, // 发送文件
    SEND_VOICE_MESSAGE, // 发送语音
    SEND_POLL_MESSAGE, // 发送投票

    REDIRECT_MESSAGE, // 转发消息
    PIN_MESSAGES, // 置顶消息

    SEND_MEDIA(SEND_PHOTOS_MESSAGE, SEND_VIDEOS_MESSAGE, SEND_MUSIC_MESSAGE, SEND_STICKERS_MESSAGE, SEND_FILES_MESSAGE, SEND_VOICE_MESSAGE, SEND_POLL_MESSAGE),
    SEND_MESSAGES(SEND_TEXT_MESSAGE, SEND_MEDIA),

    INVITE,
    EDIT_TITLE,
    PROMOTE_ADMIN,
    EDIT_NAME,

    ADMIN(INVITE, EDIT_NAME, PROMOTE_ADMIN, EDIT_NAME, REDIRECT_MESSAGE, PIN_MESSAGES),
    MEMBER_DEFAULT(SEND_MESSAGES, REDIRECT_MESSAGE, PIN_MESSAGES, INVITE);

    private final ChannelPermission[] subPermissions;

    ChannelPermission(ChannelPermission... sub) {
        this.subPermissions = sub;
    }

    public static @NotNull Set<ChannelPermission> calculatePermissions(ChannelPermission @NotNull ... permissions) {
        Set<ChannelPermission> permissionList = new HashSet<>();
        for (ChannelPermission permission : permissions) {
            if (permission.getSubPermissions().length > 0) {
                // calculate subPermissions
                permissionList.addAll(calculateSub(permission));
            }
            permissionList.add(permission);
        }
        return permissionList;
    }

    private static Set<ChannelPermission> calculateSub(ChannelPermission permission) {
        return calculateSub(permission, Set.of());
    }

    private static Set<ChannelPermission> calculateSub(@NotNull ChannelPermission permission, Set<ChannelPermission> current) {
        ChannelPermission[] subPermissions1 = permission.getSubPermissions();
        if (subPermissions1.length == 0) {
            return Set.of();
        }
        Set<ChannelPermission> subPermissions = new HashSet<>();
        for (ChannelPermission subPermission : subPermissions1) {
            subPermissions.add(subPermission);
            if (!current.contains(subPermission) && subPermission.getSubPermissions().length > 0) {
                subPermissions.addAll(calculateSub(subPermission, subPermissions));
            }
        }
        return subPermissions;
    }
}

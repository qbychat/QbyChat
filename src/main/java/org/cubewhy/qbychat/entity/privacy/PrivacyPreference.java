package org.cubewhy.qbychat.entity.privacy;

import lombok.Data;
import org.cubewhy.qbychat.entity.User;

import java.util.List;

@Data
public class PrivacyPreference {
    private Preference bio;
    private Preference invites;
    private Preference calls;
    private Preference email; // hide/show your primary email in your profile
    private Preference redirectedMessages;

    static enum Status {
        EVERYBODY, NOBODY, CONTACTS
    }

    @Data
    public static class Preference {
        private Status defaultPreference = Status.EVERYBODY;

        private List<User> always = List.of();
        private List<User> never = List.of();
    }
}

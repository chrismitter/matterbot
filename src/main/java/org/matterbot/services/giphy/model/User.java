package org.matterbot.services.giphy.model;

import lombok.Data;

@Data
public class User {
    private String avatarUrl;
    private String bannerUrl;
    private String profileUrl;
    private String username;
    private String displayName;
    private String twitter;
}

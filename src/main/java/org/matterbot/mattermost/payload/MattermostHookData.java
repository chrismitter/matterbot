package org.matterbot.mattermost.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MattermostHookData {
    private String channel;
    private String username;
    private String icon_url;
    private String text;
}

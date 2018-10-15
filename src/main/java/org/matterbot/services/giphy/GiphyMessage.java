package org.matterbot.services.giphy;

import org.matterbot.mattermost.MattermostMessage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GiphyMessage implements MattermostMessage {

    private String giphyUrl;

    private String caption;

    private final String NEWLINE = "\n";

    @Override
    public String getMessage() {
        return caption + NEWLINE + giphyUrl;
    }
}

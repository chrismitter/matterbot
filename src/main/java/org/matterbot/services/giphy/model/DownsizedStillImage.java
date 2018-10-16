package org.matterbot.services.giphy.model;

import lombok.Data;

@Data
public class DownsizedStillImage {
    private String url;
    private String width;
    private String height;
}

package org.matterbot.services.giphy.model;

import lombok.Data;

@Data
public class FixedWidthStillImage {
    private String url;
    private String width;
    private String height;
}

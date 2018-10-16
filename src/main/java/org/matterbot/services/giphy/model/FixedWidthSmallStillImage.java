package org.matterbot.services.giphy.model;

import lombok.Data;

@Data
public class FixedWidthSmallStillImage {
    private String url;
    private String width;
    private String height;
}

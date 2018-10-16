package org.matterbot.services.giphy.model;

import lombok.Data;

@Data
public class DownsizedLargeImage {
    private String url;
    private String width;
    private String height;
    private String size;
}

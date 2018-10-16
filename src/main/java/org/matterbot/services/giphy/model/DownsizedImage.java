package org.matterbot.services.giphy.model;

import lombok.Data;

@Data
public class DownsizedImage {
    private String url;
    private String width;
    private String height;
    private String size;
}

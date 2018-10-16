package org.matterbot.services.giphy.model;

import lombok.Data;

@Data
public class FixedWidthDownsampledImage {
    private String url;
    private String width;
    private String height;
    private String size;
    private String webp;
    private String webp_size;
}

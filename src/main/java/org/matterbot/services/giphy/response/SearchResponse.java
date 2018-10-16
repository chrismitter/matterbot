package org.matterbot.services.giphy.response;

import lombok.Data;
import org.matterbot.services.giphy.model.Gif;
import org.matterbot.services.giphy.model.Meta;
import org.matterbot.services.giphy.model.Pagination;

@Data
public class SearchResponse {
    private Gif[] data;
    private Pagination pagination;
    private Meta meta;
}

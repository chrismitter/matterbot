package org.matterbot.services.giphy.response;

import lombok.Data;
import org.matterbot.services.giphy.model.Gif;
import org.matterbot.services.giphy.model.Meta;
import org.matterbot.services.giphy.model.Pagination;

import java.util.List;

@Data
public class SearchResponse {
    private List<Gif> data;
    private Pagination pagination;
    private Meta meta;
}

package org.matterbot.services;

import java.util.List;

public interface URLQueryService {
    enum Strategy {TRENDING, RANDOM, SEARCH}

    String getUrl(Strategy strategy);

    String getUrl(Strategy strategy, String term);

    List<String> getUrlList(String term);
}

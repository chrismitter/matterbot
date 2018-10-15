package org.matterbot.services;

public interface URLQueryService {
    enum Strategy { TRENDING, RANDOM, SEARCH }

    String getUrl(Strategy strategy);
    String getUrl(Strategy strategy, String term);
}

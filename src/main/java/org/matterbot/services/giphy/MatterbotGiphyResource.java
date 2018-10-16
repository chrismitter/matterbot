package org.matterbot.services.giphy;

import org.matterbot.mattermost.MattermostService;
import org.matterbot.services.URLQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class MatterbotGiphyResource {
    private URLQueryService giphyService;

    private MattermostService mattermostService;

    @Autowired
    private MatterbotGiphyResource(
            MattermostService mattermostService,
            URLQueryService giphyService){
        this.mattermostService = mattermostService;
        this.giphyService = giphyService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/giphy/trending"})
    public ResponseEntity<String> postGiphyTrendingMessage() throws IOException {
        String giphyUrl = giphyService.getUrl(URLQueryService.Strategy.TRENDING);
        GiphyMessage giphyMessage = GiphyMessage.builder()
                .caption("trending")
                .giphyUrl(giphyUrl)
                .build();
        return mattermostService.sendMessage(giphyMessage);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/giphy/random"})
    public ResponseEntity<String> postGiphyRandomMessage() throws IOException{
        String giphyUrl = giphyService.getUrl(URLQueryService.Strategy.RANDOM);
        GiphyMessage giphyMessage = GiphyMessage.builder()
                .caption("random")
                .giphyUrl(giphyUrl)
                .build();
        return mattermostService.sendMessage(giphyMessage);
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/giphy/search"})
    public ResponseEntity<String> postGiphySearchResult(@RequestParam("query") String query) throws IOException{
        String giphyUrl = giphyService.getUrl(URLQueryService.Strategy.SEARCH, query);
        GiphyMessage giphyMessage = GiphyMessage.builder()
                .caption(query)
                .giphyUrl(giphyUrl)
                .build();
        return mattermostService.sendMessage(giphyMessage);
    }

}

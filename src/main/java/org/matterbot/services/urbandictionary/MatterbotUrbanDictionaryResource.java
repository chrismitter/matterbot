package org.matterbot.services.urbandictionary;

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
public class MatterbotUrbanDictionaryResource {
    private URLQueryService urbanDictionaryService;

    private MattermostService mattermostService;

    @Autowired
    private MatterbotUrbanDictionaryResource(MattermostService mattermostService, URLQueryService urbanDictionaryService){
        this.mattermostService = mattermostService;
        this.urbanDictionaryService = urbanDictionaryService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/urbandict/search"})
    public ResponseEntity<String> postUrbanSearchResult(@RequestParam("query") String query) throws IOException{
        String urbanUrl = urbanDictionaryService.getUrl(URLQueryService.Strategy.SEARCH, query);
        UrbanDictionaryMessage urbanMessage = UrbanDictionaryMessage.builder()
                .caption(query)
                .description(urbanUrl)
                .build();
        return mattermostService.sendMessage(urbanMessage);
    }
}

package org.matterbot.services.txt2ascii;

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
public class TxtToAsciiResource {
    private URLQueryService txtToAsciiService;

    private MattermostService mattermostService;

    @Autowired
    private TxtToAsciiResource(MattermostService mattermostService, URLQueryService txtToAsciiService) {
        this.mattermostService = mattermostService;
        this.txtToAsciiService = txtToAsciiService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/ascii/search"})
    public ResponseEntity<String> postTxtToAsciiResult(@RequestParam("query") String query) throws IOException {
        return mattermostService.sendMessage(TxtToAsciiMessage.builder()
                .caption(query)
                .description("```" + txtToAsciiService.getUrl(URLQueryService.Strategy.SEARCH, query) + "```")
                .build());
    }
}

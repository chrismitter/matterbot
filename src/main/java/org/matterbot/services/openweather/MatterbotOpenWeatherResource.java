package org.matterbot.services.openweather;

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
public class MatterbotOpenWeatherResource {
    private URLQueryService openWeatherService;

    private MattermostService mattermostService;

    @Autowired
    private MatterbotOpenWeatherResource(MattermostService mattermostService, URLQueryService openWeatherService) {
        this.mattermostService = mattermostService;
        this.openWeatherService = openWeatherService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/openweather/location"})
    public ResponseEntity<String> getWeatherMessage(@RequestParam("query") String query) throws IOException {
        String icon = openWeatherService.getUrl(null, query);
        OpenWeatherMessage giphyMessage = OpenWeatherMessage.builder()
                .caption("Weather for '" + query + "'")
                .icon(icon)
                .build();
        return mattermostService.sendMessage(giphyMessage);
    }

}

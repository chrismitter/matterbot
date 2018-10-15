package org.matterbot.mattermost;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Slf4j
@Controller
public class MatterbotResource {

    private MattermostService mattermostService;

    @Autowired
    private MatterbotResource(MattermostService mattermostService){
        this.mattermostService = mattermostService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call/text/{text}"})
    public ResponseEntity<String> postMessage(@PathVariable("text") String text) throws IOException{
        return mattermostService.sendMessage(text);
    }
}

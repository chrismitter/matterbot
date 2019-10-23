package org.matterbot.mattermost;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
public class MatterbotResource {

    private MattermostService mattermostService;

    @Autowired
    private MatterbotResource(MattermostService mattermostService){
        this.mattermostService = mattermostService;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/call"})
    public ResponseEntity<String> postMessage(@RequestParam("text") String text) throws IOException{
        return mattermostService.sendMessage(text);
    }
}

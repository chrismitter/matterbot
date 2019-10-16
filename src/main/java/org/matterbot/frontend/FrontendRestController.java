package org.matterbot.frontend;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendRestController {

    @GetMapping("/")
    public String greeting(Model model) {
        return "index";
    }

}
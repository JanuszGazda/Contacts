package com.janusz.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping("/")
    public String home(HttpServletRequest request){
        request.setAttribute("mode", "MODE_HOME");
        return "index";
    }

}

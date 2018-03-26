package com.janusz.Controller;

import com.janusz.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/")
    public String index(HttpServletRequest request){
        request.setAttribute("tasks", personService.getAllPersons());

        return "index";
    }

}

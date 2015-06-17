package com.easytutor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by root on 17.06.15.
 */
@Controller
public class LoginController {

    @RequestMapping({"/","/home"})
    public String goHome(){
        return "index";
    }
}

package com.easytutor.controllers;

import com.easytutor.utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

/**
 * Created by root on 17.06.15.
 */
@Controller
public class LoginController {

    @RequestMapping({"/"})
    public String goHome(){
        HibernateUtil.getSessionFactory();
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "login")
    public String login(@RequestParam("login") String login, @RequestParam("password") String pass){
        Logger.getLogger(LoginController.class.getName()).info("Login and pass: " + login + " pass " + pass);
        return "index";
    }
}

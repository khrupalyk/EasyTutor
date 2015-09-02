package com.easytutor.controllers;

import com.easytutor.dao.UserDAO;
import com.easytutor.models.RegisteredUser;
import com.easytutor.models.User;
import com.easytutor.models.UserMessage;
import com.easytutor.utils.ApplicationContextProvider;
import com.easytutor.validators.UserFormValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by root on 17.06.15.
 */
@Controller
public class LoginController {

    UserDAO userDAO = (UserDAO) ApplicationContextProvider.getApplicationContext().getBean(UserDAO.class);
    UserFormValidator userFormValidator = (UserFormValidator) ApplicationContextProvider.getApplicationContext().getBean(UserFormValidator.class);


    @RequestMapping({"/"})
    public String goHome() {
        return "WEB-INF/pages/index";
    }


    @RequestMapping("/signup")
    public ModelAndView signupTO(){

        ModelAndView modelAndView = new ModelAndView("WEB-INF/pages/signup");

        modelAndView.addObject("user", new RegisteredUser());
        return modelAndView;
    }

    //TODO This validator must check only signup request

    @InitBinder(value = "user")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userFormValidator);
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@ModelAttribute("user")  @Validated RegisteredUser user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ModelAndView("WEB-INF/pages/signup");
        }

        User newUSer = new User();
        newUSer.setEnabled(true);
        newUSer.setPassword(user.getPassword());
        newUSer.setUsername(user.getUsername());

        userDAO.addUser(newUSer);
        return new ModelAndView("WEB-INF/pages/signupComplete");

    }

    @RequestMapping(value = "contact")
    public ModelAndView goToContactView() {
        ModelAndView modelAndView = new ModelAndView("WEB-INF/pages/contact");
        modelAndView.addObject("userMessage", new UserMessage());
        return modelAndView;
    }

    @RequestMapping(value = "contact-with-admin", method = RequestMethod.POST)
    public String registerNewMessage(@ModelAttribute("user") @Validated  UserMessage userMessage){

        System.out.println(userMessage);
        //TODO Send message to my email

        return "/WEB-INF/pages/sendMessageComplete";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("WEB-INF/pages/login");

        return model;

    }
    @RequestMapping(value = "error")
    public String error(){

        return "/WEB-INF/pages/internalServerError";
    }

    @RequestMapping(value = "access-denied")
    public String accessDenied(){

        return "/WEB-INF/pages/accessDenied";
    }

}

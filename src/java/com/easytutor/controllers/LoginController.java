package com.easytutor.controllers;

import com.easytutor.dao.UserDAO;
import com.easytutor.models.RegisteredUser;
import com.easytutor.models.User;
import com.easytutor.models.UserMessage;
import com.easytutor.utils.ApplicationContextProvider;
import com.easytutor.validators.UserFormValidator;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

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

    @RequestMapping({"/not-found"})
    public String goToNofFound() {
        return "WEB-INF/pages/notFound";
    }


    @RequestMapping("/signup")
    public ModelAndView signupTO() {

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
    public ModelAndView signup(@ModelAttribute("user") @Validated RegisteredUser user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("WEB-INF/pages/signup");
        }

        User newUSer = new User();
        newUSer.setEnabled(true);
        newUSer.setPassword(user.getPassword());
        newUSer.setUsername(user.getUsername());
        newUSer.setFirstName(user.getFirstName());
        newUSer.setLastName(user.getLastName());
        newUSer.setEmail(user.getEmail());

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
    public String registerNewMessage(@ModelAttribute("userMessage") @Validated UserMessage userMessage) {

        System.out.println(userMessage);

        final String username = "easyturor@gmail.com";
        final String password = "moskit22212";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("easytutor@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("khrupalik@gmail.com"));
            message.setSubject("EasyTutor");
            message.setText("User " + userMessage.getName() + ", with email " + userMessage.getEmail() + " Send message: " + userMessage.getMessage());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


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
    public String error() {

        return "/WEB-INF/pages/internalServerError";
    }

    @RequestMapping(value = "access-denied")
    public String accessDenied() {

        return "/WEB-INF/pages/accessDenied";
    }

    @Secured("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "users")
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView("WEB-INF/pages/users");

        modelAndView.addObject("users", userDAO.getAllUsers());
        return modelAndView;
    }


}

package com.sportsstore.web.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("account")
public class LoginController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }
}

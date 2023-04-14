package org.project.java.springfotoalbum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(){
        return "/logpages/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "/logpages/logout";
    }
}

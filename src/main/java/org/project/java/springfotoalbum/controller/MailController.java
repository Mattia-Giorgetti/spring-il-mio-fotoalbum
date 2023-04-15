package org.project.java.springfotoalbum.controller;

import org.project.java.springfotoalbum.model.Mail;
import org.project.java.springfotoalbum.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mails")
public class MailController {

    @Autowired
    private MailService mailService;

   @GetMapping
   public String index(Model model){
        model.addAttribute("mails",mailService.getAllMails());
        return "/mails/index";
    }
}

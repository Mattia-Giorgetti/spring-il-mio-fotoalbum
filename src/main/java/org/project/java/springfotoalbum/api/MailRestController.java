package org.project.java.springfotoalbum.api;

import jakarta.validation.Valid;
import org.project.java.springfotoalbum.model.Mail;
import org.project.java.springfotoalbum.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/mails")
public class MailRestController {

    @Autowired
    MailService mailService;

    @PostMapping
    public Mail storeMail(@Valid @RequestBody Mail mail){
        return mailService.createMail(mail);
    }
}

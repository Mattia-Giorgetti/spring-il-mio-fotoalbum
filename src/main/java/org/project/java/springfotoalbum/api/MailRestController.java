package org.project.java.springfotoalbum.api;

import jakarta.validation.Valid;
import org.project.java.springfotoalbum.model.Mail;
import org.project.java.springfotoalbum.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/api/mails")
public class MailRestController {

    @Autowired
    MailService mailService;

    @PostMapping
    public Mail storeMail(@Valid @RequestBody Mail mail, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return mailService.createMail(mail);
    }
}

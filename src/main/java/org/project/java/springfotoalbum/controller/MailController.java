package org.project.java.springfotoalbum.controller;

import org.project.java.springfotoalbum.exceptions.MailNotFoundException;
import org.project.java.springfotoalbum.exceptions.PhotoNotFoundException;
import org.project.java.springfotoalbum.model.FlashMessage;
import org.project.java.springfotoalbum.model.Mail;
import org.project.java.springfotoalbum.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){
       try {
           Mail mail = mailService.getMailById(id);
           model.addAttribute("mail", mail);
           return "/mails/show";
       } catch (MailNotFoundException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
    }
    @GetMapping("/delete/{id}")
    public String deleteMail(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            boolean success = mailService.deleteMail(id);
            if (success){
                redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.SUCCESS, "Mail deleted"));
            } else {
                redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.ERROR, "Unable to delete this mail"));
            }
        } catch (PhotoNotFoundException e){
            redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.ERROR, "Can't find mail with id " + id));
        }
        return "redirect:/mails";
    }
}

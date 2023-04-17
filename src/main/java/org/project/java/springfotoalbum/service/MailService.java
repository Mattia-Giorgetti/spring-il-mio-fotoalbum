package org.project.java.springfotoalbum.service;

import org.project.java.springfotoalbum.exceptions.MailNotFoundException;
import org.project.java.springfotoalbum.exceptions.PhotoNotFoundException;
import org.project.java.springfotoalbum.model.Mail;
import org.project.java.springfotoalbum.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MailService {

    @Autowired
    MailRepository mailRepository;

    public List<Mail> getAllMails(){
        return mailRepository.findAll();
    }

    public Mail getMailById(Integer id){
        Optional<Mail> mail = mailRepository.findById(id);
        if (mail.isPresent()){
            return mail.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Mail createMail(Mail formMail){
        Mail newMail = new Mail();
        newMail.setMail(formMail.getMail());
        newMail.setMessage(formMail.getMessage());
        return mailRepository.save(newMail);
    }
    public boolean deleteMail(Integer id) throws MailNotFoundException {
        mailRepository.findById(id).orElseThrow(()-> new MailNotFoundException(Integer.toString(id)));
        try {
            mailRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}

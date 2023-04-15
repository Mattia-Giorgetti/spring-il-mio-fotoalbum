package org.project.java.springfotoalbum.repository;

import org.project.java.springfotoalbum.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Integer> {
}

package com.company.notification.controller;

import com.company.notification.entity.DTO.EmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.company.notification.config.Constants.SENDER_EMAIL;

@RestController
@RequestMapping
public class EmailController {


    private final Logger log = LoggerFactory.getLogger(EmailController.class);
    private final JavaMailSender mailSender;

    @Autowired
    public EmailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * The method to send an email
     *
     * @param emailDTO EmailDTO
     * @return ResponseEntity with String
     */
    @PostMapping("/sendEmail")
    public ResponseEntity sendMail(@Validated @RequestBody EmailDTO emailDTO) {
        log.info("Send data to email: {}", emailDTO);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailDTO.getRecipients().toArray(new String[0]));
        email.setSubject(emailDTO.getSubject());
        email.setText(emailDTO.getMessage());
        email.setFrom(SENDER_EMAIL);
        email.setReplyTo(SENDER_EMAIL);
        mailSender.send(email);
        return ResponseEntity.ok("The message was sent");
    }

}

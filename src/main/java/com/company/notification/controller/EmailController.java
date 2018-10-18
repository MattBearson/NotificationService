package com.company.notification.controller;

import com.company.notification.Service.EmailService;
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

@RestController
@RequestMapping
public class EmailController {


    private final Logger log = LoggerFactory.getLogger(EmailController.class);
    private final JavaMailSender mailSender;
    private final EmailService emailService;

    @Autowired
    public EmailController(JavaMailSender mailSender, EmailService emailService) {
        this.mailSender = mailSender;
        this.emailService = emailService;
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
        SimpleMailMessage email = emailService.createEmail(emailDTO);
        mailSender.send(email);
        return ResponseEntity.ok("The message was sent");
    }

    @PostMapping("/sendAsyncEmail")
    public ResponseEntity sendAsyncMail(@Validated @RequestBody EmailDTO emailDTO) {
        log.info("Send data to email: {}", emailDTO);
        emailService.createAndSendEmail(emailDTO);
        return ResponseEntity.ok("The message was sent");
    }

}

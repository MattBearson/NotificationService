package com.company.notification.Service;

import com.company.notification.controller.EmailController;
import com.company.notification.entity.DTO.EmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.company.notification.config.Constants.SENDER_EMAIL;

@Service
public class EmailService {

    private final Logger log = LoggerFactory.getLogger(EmailController.class);
    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * In the method we are generating email
     *
     * @param emailDTO EmailDTO
     * @return email SimpleMailMessage
     */
    public SimpleMailMessage createEmail(EmailDTO emailDTO) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailDTO.getRecipients().toArray(new String[0]));
        email.setSubject(emailDTO.getSubject());
        email.setText(emailDTO.getMessage());
        email.setFrom(SENDER_EMAIL);
        email.setReplyTo(SENDER_EMAIL);
        return email;
    }

    @Async
    public void createAndSendEmail(EmailDTO emailDTO) {
        log.debug("Starting async call to create and send email: {}", emailDTO);
        SimpleMailMessage email = createEmail(emailDTO);
        emailSender.send(email);
    }
}

package com.company.notification.entity.DTO;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class EmailDTO {

    @NotNull
    private String message;

    private String subject;

    @NotNull
    private List<String> recipients;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDTO emailDTO = (EmailDTO) o;
        return Objects.equals(message, emailDTO.message) &&
                Objects.equals(subject, emailDTO.subject) &&
                Objects.equals(recipients, emailDTO.recipients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, subject, recipients);
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                ", recipients=" + recipients +
                '}';
    }
}

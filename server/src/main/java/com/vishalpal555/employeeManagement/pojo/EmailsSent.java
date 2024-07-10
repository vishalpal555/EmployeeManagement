package com.vishalpal555.employeeManagement.pojo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "emails_sent")
public class EmailsSent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String toEmail;
    @Column
    private String fromEmail;
    @Column
    private String body;
    @Column
    private String subject;
    @Column
    private LocalDateTime sentTime;

    public EmailsSent() {
        this.sentTime = LocalDateTime.now();
    }

    public EmailsSent(String toEmail, String fromEmail, String body, String subject) {
        this();
        this.toEmail = toEmail;
        this.fromEmail = fromEmail;
        this.body = body;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }
}

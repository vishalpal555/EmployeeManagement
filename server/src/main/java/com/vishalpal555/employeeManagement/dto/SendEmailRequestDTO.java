package com.vishalpal555.employeeManagement.dto;

public class SendEmailRequestDTO {
    private String toEmail;
    private String body;
    private String subject;

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
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

    @Override
    public String toString() {
        return "SendEmailRequestDTO{" +
                "toEmail='" + toEmail + '\'' +
                ", body='" + body + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}

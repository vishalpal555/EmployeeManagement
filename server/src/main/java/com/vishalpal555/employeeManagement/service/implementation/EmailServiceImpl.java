package com.vishalpal555.employeeManagement.service.implementation;

import com.vishalpal555.employeeManagement.pojo.EmailsSent;
import com.vishalpal555.employeeManagement.pojo.Vendor;
import com.vishalpal555.employeeManagement.repository.EmailsSentRepoInterface;
import com.vishalpal555.employeeManagement.service.EmailService;
import com.vishalpal555.employeeManagement.service.VendorService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("spring.mail.username")
    private String senderEmail;

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private EmailsSentRepoInterface emailsSentRepoInterface;
    @Autowired
    private VendorService vendorService;

    @Override
    public void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(senderEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        emailSender.send(mimeMessage);
    }

    @Override
    public ResponseEntity<Object> sendAndSaveEmail(String to, String subject, String body){
        try{
            sendEmail(to, subject, body);
            emailsSentRepoInterface.save(new EmailsSent(to, senderEmail, body, subject));
            return ResponseEntity.ok().build();
        } catch (MessagingException e){
            LOGGER.error("handled MessagingException ", e);
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    public ResponseEntity<Object> sendAndSaveEmailList(List<String> emailList){
        try {
            emailList.parallelStream().forEach(email -> {
                Optional<Vendor> vendor = vendorService.getVendorByEmail(email);
                if (vendor.isPresent()) {
                    final String body = String.format(
                            "Sending payments to vendor %s at UPI %s",
                            vendor.get().getName(),
                            vendor.get().getUpiId()
                    );
                    final String subject = "Payment to vendor";
                    sendAndSaveEmail(vendor.get().getEmail(), subject, body);
                } else {
                    LOGGER.error("Email:{} not present", email);
                }
            });
            return ResponseEntity.ok().build();
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    public ResponseEntity<List<EmailsSent>> getAllMails(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(emailsSentRepoInterface.findAll());
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    public ResponseEntity<List<EmailsSent>> getMailByRecipient(String recipient){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(emailsSentRepoInterface.findEmailsByRecipient(recipient));
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    public ResponseEntity<List<EmailsSent>> getMailBySubject(String subject){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(emailsSentRepoInterface.findEmailsBySubject(subject));
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    public ResponseEntity<EmailsSent> getMailById(Long emailId){
        try{
            Optional<EmailsSent> emailsSentOptional = emailsSentRepoInterface.findById(emailId);
            return emailsSentOptional
                    .map(emailsSent ->
                            ResponseEntity.status(HttpStatus.OK).body(emailsSent)
                    )
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e){
            LOGGER.error("handled exception ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

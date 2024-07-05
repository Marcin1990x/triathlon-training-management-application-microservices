package pl.koneckimarcin.email_service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailAddress;

    @Scheduled(fixedRate = 20000)
    //public void sendEmailWithTrainingPlan(String emailAddress, String emailData) throws MessagingException {
    public void sendEmailWithTrainingPlan() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setTo(emailAddress);
        messageHelper.setSubject("Training incoming"); // to be set properly
        messageHelper.setText("Test email"); // emailData ultimately

        javaMailSender.send(message);
    }
}

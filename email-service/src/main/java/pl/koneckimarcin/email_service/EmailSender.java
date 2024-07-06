package pl.koneckimarcin.email_service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.koneckimarcin.email_service.messaging.TrainingPlanMessage;

@Service
public class EmailSender {

    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithTrainingPlan(String emailAddress, TrainingPlanMessage emailData)
            throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setTo(emailAddress);
        messageHelper.setSubject("TriathlonTrainingManagement Application : You have planned training for tomorrow");
        messageHelper.setText(emailContent(emailData),true);

        javaMailSender.send(message);
        logger.info("Email with message: " + emailData.getMessageId() + " sent to the address " + emailAddress);
    }
    private String emailContent(TrainingPlanMessage emailData) {
        return  "<html>" +
                "<body>" +
                "<h1>Training planned for tomorrow: </h1>" +
                "<p><strong>Training Name:</strong> " + emailData.getTrainingName() + "</p>" +
                "<p><strong>Training Type:</strong> " + emailData.getTrainingType() + "</p>" +
                "<p><strong>Training Description:</strong> " + emailData.getTrainingDescription() + "</p>" +
                "</body>" +
                "</html>";
    }
}

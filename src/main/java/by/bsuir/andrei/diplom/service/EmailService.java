package by.bsuir.andrei.diplom.service;

import by.bsuir.andrei.diplom.model.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setFrom("diplomapplication@bsuir.by");
            helper.setSubject("Confirm your email");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }
}

package com.example.companyemployeespring.service;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final MailSender mailSender;

    @Async
    public void sendMessage(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }

//    @Async
//    public void setHtmlEmail(String to, String subject, Employee employee, String link, String templateName, Locale locale) throws MessagingException {
//        final Context ctx = new Context(locale);
//        ctx.setVariable("employee", employee);
//        ctx.setVariable("url", link);
//
//        final String htmlContent = this.templateEngine.process(templateName, ctx);
//
//        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        final MimeMessageHelper message =
//                new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
//        message.setSubject(subject);
//        message.setFrom("javaForTest2018@gmail.com");
//        message.setTo(to);
//        message.setText(htmlContent, true);
//
//        this.javaMailSender.send(mimeMessage);
//    }
}

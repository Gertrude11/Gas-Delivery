package com.Gertrude.Final.Project.email;

import com.Gertrude.Final.Project.model.CustomerOrder;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void sendPaymentConfirmationEmail(CustomerOrder customerOrder) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(customerOrder.getCustomerEmail());
            helper.setSubject("Payment Confirmation");
            String content = buildPaymentConfirmationEmail(customerOrder);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String buildPaymentConfirmationEmail(CustomerOrder customerOrder) {
        Context context = new Context();
        context.setVariable("order", customerOrder);
        return templateEngine.process("payment-confirmation-template", context);
    }
}

package com.md29.microservices.notification.service;

import com.md29.microservices.order.events.OrderPlacedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        log.info("Got message from kafka producer topic{}", orderPlacedEvent);
        // Send email to customer
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springshop@email.com");
            messageHelper.setTo(orderPlacedEvent.getEmail().toString());
            messageHelper.setSubject(String.format("Your order with OrderNumber %s is placed successfully", orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
                    Hi,
                    Your order with Order Number: %s is placed successfully!
                    
                    Best regards,
                    Spring shop.
                    """, orderPlacedEvent.getOrderNumber()));
        };
        try {
            javaMailSender.send(messagePreparator);
            log.info("Order Notification email sent!");
        } catch (MailException e) {
            log.error("Exception occurred while sending mail!", e);
            throw new RuntimeException("Exception occurred while sending email from springshop.email", e);
        }
    }

}

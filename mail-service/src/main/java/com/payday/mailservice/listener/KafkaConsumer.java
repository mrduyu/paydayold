package com.payday.mailservice.listener;

import com.payday.mailservice.controller.EmailController;
import com.payday.mailservice.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "user",groupId = "group_id")
    public void consume(String message) throws IOException, MessagingException {
        LOGGER.info("Message consumed: " + message);
        MailService mailService = new MailService();
        try
        {
            mailService.sendmail(message);
        }
        catch (Exception ex)
        {
            LOGGER.error("Error sending mail: " + ex.toString());
        }
    }
}

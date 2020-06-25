package com.payday.mailservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class EmailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "user";

    @RequestMapping(value = "/sendmail/{message}", method = RequestMethod.GET)
    public String sendMessage(@PathVariable String message) {
        LOGGER.info("Mail message:" +message);
        kafkaTemplate.send(TOPIC, message);
        return "Mail sent.";
    }



}
package com.example.websocket.service;


import com.example.websocket.entities.Greeting;
import com.example.websocket.entities.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    Logger logger = LoggerFactory.getLogger(GreetingController.class);


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        logger.info("greeting()");
        logger.info("message received: {}", message.getName());
        Thread.sleep(1000); // simulated delay
        Greeting response = new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        logger.info("response created: {}", response);
        return response;
    }

}
package com.message.app.springbootsmsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class SmsController {

    @Autowired
    SmsService smsService;

    @Autowired
    private SimpMessagingTemplate webSocket;

    private final String TOPIC_DESTINATION = "/lesson/sms";

    @RequestMapping(value =  "/sms", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void smsSubmit(@RequestBody SmsPOJO sms){
        try{
            smsService.send(sms);
        }
        catch(Exception e){
            webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ":Error sending SMS: "+sms.getTo());
            throw e;
        }
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() +": SMS sent successfully!: "+sms.getTo());
    }

    @RequestMapping(value = "/smscallback", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void smsCallback(@RequestBody MultiValueMap<String, String> map){
        smsService.receive(map);
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Twilio has made a callback request!Here are the details");
    }


    private Object getTimeStamp() {
        return DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }

}

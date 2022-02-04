package com.message.app.springbootsmsapp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.security.MessageDigest;

@Component
public class SmsService {

    private final String ACCOUNT_SID = "ACe79475592fe13a12cc156ac282bd9b27";
    private final String AUTH_TOKEN = "d71948dec871c242aca34df776157364";
    private final String FROM_NUMBER = "+19034855849";

    public void send(SmsPOJO sms){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage()).create();
        System.out.println("Here is my id: "+message.getSid());
    }

    public void receive(MultiValueMap<String, String> smscallback){

    }

}

package com.message.app.springbootsmsapp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.security.MessageDigest;

@Component
public class SmsService {

    private final String ACCOUNT_SID = "ACe76349022fe13a12cc732ac282bd9b27";
    private final String AUTH_TOKEN = "d93412frb871c242aca34df923512320";
    private final String FROM_NUMBER = "+19653475849";

    public void send(SmsPOJO sms){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage()).create();
        System.out.println("Here is my id: "+message.getSid());
    }

    public void receive(MultiValueMap<String, String> smscallback){

    }

}

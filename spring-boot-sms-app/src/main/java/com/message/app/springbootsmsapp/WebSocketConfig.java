package com.message.app.springbootsmsapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
// websocket is used for two-way communication between user's browser and server.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //implemented a websocketmessagebrokerconfig interface as it contains methods to
    // configure/establish connection with websocket, configuremesgBroker() used to route/send msg
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/lesson");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

}

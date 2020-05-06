package com.capstone.sheepsheadbackend.webSockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private Environment env;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        enabling SockJS fallback options so that alternate transports can be used if WebSocket is not available
        // waits for connections to "/port.." to subscribe to on the client
        String ip = env.getProperty("server.address");
        String url = "http://" + ip + ":4200";
        registry.addEndpoint("/ws").setAllowedOrigins(url).withSockJS();
//        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // message broker to carry the messages back to the client on destinations prefixed with /game
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

}

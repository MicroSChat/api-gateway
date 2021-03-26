package com.microschat.mscapigateway.connectivity;

import com.microschat.commonlibrary.UserInformationMessage;
import com.microschat.commonlibrary.connectivity.ConnectivityConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class RabbitMQConnector {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQConnector(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(ConnectivityConstant.APPLICATION_EXCHANGE);
    }

    public void sendRegistrationRequest(UserInformationMessage userInformationMessage){
        log.info("Sending registration request to queues with routing key {}", ConnectivityConstant.REGISTRATION_USER_ROUTING_KEY);
        rabbitTemplate.convertAndSend(ConnectivityConstant.APPLICATION_EXCHANGE, ConnectivityConstant.REGISTRATION_USER_ROUTING_KEY, userInformationMessage);
    }
}

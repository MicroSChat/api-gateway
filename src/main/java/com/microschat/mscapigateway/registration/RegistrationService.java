package com.microschat.mscapigateway.registration;

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
public class RegistrationService {

    private final RabbitTemplate rabbitTemplate;

    public RegistrationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendRegistrationRequest(UserInformationMessage userInformationMessage){
        log.info("Sending registration request to queues with routing key {}", ConnectivityConstant.USER_REGISTRATION_ROUTING_KEY);
        rabbitTemplate.convertAndSend(ConnectivityConstant.APPLICATION_EXCHANGE, ConnectivityConstant.USER_REGISTRATION_ROUTING_KEY, userInformationMessage);
    }
}

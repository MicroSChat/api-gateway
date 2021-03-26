package com.microschat.mscapigateway.authentication;

import com.microschat.commonlibrary.UserInformationMessage;
import com.microschat.commonlibrary.connectivity.ConnectivityConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
@Slf4j
public class AuthenticationService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public AuthenticationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String authenticateUser(UserInformationMessage userInformationMessage){
        String token = (String) rabbitTemplate.convertSendAndReceive(ConnectivityConstant.APPLICATION_EXCHANGE,
                ConnectivityConstant.USER_AUTHENTICATION_ROUTING_KEY,
                userInformationMessage);
        log.info("Got token {} from auth service", token);
        return token;
    }
}


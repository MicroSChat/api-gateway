package com.microschat.mscapigateway.registration;

import com.microschat.commonlibrary.UserInformationMessage;
import com.microschat.mscapigateway.connectivity.RabbitMQConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final RabbitMQConnector rabbitMQConnector;

    @Autowired
    public RegistrationController(RabbitMQConnector rabbitMQConnector) {
        this.rabbitMQConnector = rabbitMQConnector;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserInformationMessage userInformationMessage){
        rabbitMQConnector.sendRegistrationRequest(userInformationMessage);
    }
}

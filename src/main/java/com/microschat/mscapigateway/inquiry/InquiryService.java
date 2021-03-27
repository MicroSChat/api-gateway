package com.microschat.mscapigateway.inquiry;

import com.microschat.commonlibrary.UserInformationMessage;
import com.microschat.commonlibrary.connectivity.ConnectivityConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InquiryService {
    private final RabbitTemplate rabbitTemplate;

    public InquiryService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public UserInformationMessage inquireUser(String username){
        UserInformationMessage informationMessage = (UserInformationMessage) rabbitTemplate.convertSendAndReceive(ConnectivityConstant.APPLICATION_EXCHANGE,
                ConnectivityConstant.USER_INQUIRY_ROUTING_KEY,
                username);

        log.info("Inquired user for username {}, got information message {}", username, informationMessage);
        if (informationMessage == null){
            throw new AmqpRejectAndDontRequeueException("No user found for username");
        }
        return informationMessage;
    }
}

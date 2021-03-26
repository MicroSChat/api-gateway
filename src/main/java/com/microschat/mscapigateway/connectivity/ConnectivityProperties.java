package com.microschat.mscapigateway.connectivity;

import com.microschat.commonlibrary.connectivity.ConnectivityConstant;
import lombok.Data;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.connectivity")
@Data
public class ConnectivityProperties {
    private String authBaseUrl;
    private String authRegister;

    private String pdsBaseUrl;
    private String pdsRegister;

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(ConnectivityConstant.APPLICATION_EXCHANGE);
    }
}

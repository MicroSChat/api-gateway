package com.microschat.mscapigateway.connectivity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.connectivity")
@Data
public class ConnectivityProperties {
    private String authBaseUrl;
    private String authRegister;

    private String pdsBaseUrl;
    private String pdsRegister;
}

package com.microschat.mscapigateway.authentication;

import com.microschat.commonlibrary.UserInformationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@RequestBody UserInformationMessage userInformationMessage){
        return authenticationService.authenticateUser(userInformationMessage);
    }
}

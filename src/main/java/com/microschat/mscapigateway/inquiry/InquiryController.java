package com.microschat.mscapigateway.inquiry;

import com.microschat.commonlibrary.UserInformationMessage;
import com.microschat.mscapigateway.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class InquiryController {

    private final InquiryService inquiryService;
    private final AuthenticationService authenticationService;

    @Autowired
    public InquiryController(InquiryService inquiryService, AuthenticationService authenticationService) {
        this.inquiryService = inquiryService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserInformationMessage> inquireUserByUsername(@RequestHeader String token, @RequestParam String username){
        if (!authenticationService.validateToken(token)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(inquiryService.inquireUser(username));
    }
}

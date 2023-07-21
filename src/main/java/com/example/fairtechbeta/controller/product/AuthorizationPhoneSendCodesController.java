package com.example.fairtechbeta.controller.product;

import com.example.fairtechbeta.service.product.AuthorizationPhoneSendCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Malikov Azizjon    Fairtech-Beta       20.07.2023       16:17
 */


@RestController
@RequestMapping("/api/v.1/product-code")
public class AuthorizationPhoneSendCodesController {

    @Autowired
    public AuthorizationPhoneSendCodesService authorizationPhoneSendCodesService;


    // send sms code
    @PostMapping("/send-code")
    public ResponseEntity<String> sendsms(@RequestParam(name = "phoneNumber") String phoneNumber){
        return authorizationPhoneSendCodesService.sendMessage(phoneNumber);
    }


    // checking sms code
    @PostMapping("/check-code")
    public ResponseEntity<String> checkCode(@RequestParam(name = "code") Long code,
                                            @RequestParam(name = "phoneNumber") String phoneNumber){
        return authorizationPhoneSendCodesService.checkCode(phoneNumber, code);
    }

}

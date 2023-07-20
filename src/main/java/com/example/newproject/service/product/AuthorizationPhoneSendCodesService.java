package com.example.newproject.service.product;

import com.example.newproject.entity.product.AuthorizationPhoneSendCodes;
import com.example.newproject.repository.product.AuthorizationPhoneSendCodesRepository;
import lombok.extern.slf4j.Slf4j;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Malikov Azizjon    Fairtech-Beta       20.07.2023       16:21
 */

@Service
@Slf4j
public class AuthorizationPhoneSendCodesService {

    @Autowired
    private AuthorizationPhoneSendCodesRepository authorizationPhoneSendCodesRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${message.api}")
    private String api;

    public ResponseEntity<String> sendMessage(String phoneNumber){
        Integer randomCode = (int) Math.random() * 90000 + 10000;
        String message = message(randomCode);
        String urlTemplate = this.api + "id=" + "3110" + "&from=9789&to" + phoneNumber + "&text=" + message + "&coding=0";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity<>(headers);
        restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, String.class).getBody();
        AuthorizationPhoneSendCodes authorizationPhoneSendCodes = authorizationPhoneSendCodesRepository.getByPhoneNumber(phoneNumber);
        if (authorizationPhoneSendCodes == null)
            authorizationPhoneSendCodes = new AuthorizationPhoneSendCodes();
        authorizationPhoneSendCodes.setPhoneNumber(phoneNumber);
        Hashids hashids = new Hashids("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
        String encode = hashids.encodeHex(String.valueOf(randomCode));
        authorizationPhoneSendCodes.setCode(encode);
        authorizationPhoneSendCodes.setSendedDate(new Date());
        authorizationPhoneSendCodesRepository.save(authorizationPhoneSendCodes);
        return ResponseEntity.ok("Tasdiqlash kodi yuborildi.");
    }


    public ResponseEntity<String> checkCode(String phoneNumber, Long code){
        AuthorizationPhoneSendCodes authorizationPhoneSendCodes = authorizationPhoneSendCodesRepository.getByPhoneNumber(phoneNumber);
        if (authorizationPhoneSendCodes == null) throw new RuntimeException("Ushbu telefon raqamiga tasdiqlash kodi yuborilmagan");
        Hashids hashids = new Hashids("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
        String m = hashids.decodeHex(authorizationPhoneSendCodes.getCode());
        if (!m.equals(String.valueOf(code))){
            return ResponseEntity.ok("Siz noto`g`ri kodni kiritdingiz, iltimos qaytadan urinib ko`ring");
        }
        Date nowDate = new Date();
        Date sendedDate = authorizationPhoneSendCodes.getSendedDate();
        Date correctDate = new Date(sendedDate.getYear(), sendedDate.getMonth(), sendedDate.getDate(), sendedDate.getHours(), sendedDate.getMinutes()+2, sendedDate.getSeconds());
        if (correctDate.before(nowDate)){
            return ResponseEntity.ok("Ushbu tasdiqlash kodining muddati tugagan, iltimos qayta urinib ko`ring.");
        }
        else
            return ResponseEntity.ok("Kod tasdiqlandi.");
    }

    public String message(Integer code){
        String message = "Ushbu kodni hech kimga bermang: " + code;
        return message;
    }

}

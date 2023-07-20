package com.example.newproject.dto.product;

import com.example.newproject.entity.product.AuthorizationPhoneSendCodes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Malikov Azizjon    Fairtech-Beta       20.07.2023       16:18
 */

@Data
@NoArgsConstructor
public class AuthorizationPhoneSendCodesDto {

    private Long id;
    private String phoneNumber;
    private String sendedCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh-mm-ss", timezone = "Asia/Tashkent")
    private Date sendedDate;


    public AuthorizationPhoneSendCodesDto(AuthorizationPhoneSendCodes phoneSendCodes){
        if (phoneSendCodes.getId() != null){
            setId(phoneSendCodes.getId());
        }
        setPhoneNumber(phoneSendCodes.getPhoneNumber());
        setSendedCode(phoneSendCodes.getCode());
        setSendedDate(phoneSendCodes.getSendedDate());
    }

    public AuthorizationPhoneSendCodes convertToSenCodes(){
        AuthorizationPhoneSendCodes sendCodes = new AuthorizationPhoneSendCodes();
        return convertToSenCodes(sendCodes);
    }

    private AuthorizationPhoneSendCodes convertToSenCodes(AuthorizationPhoneSendCodes sendCodes){
        if (id != null)
            sendCodes.setId(id);
        sendCodes.setPhoneNumber(phoneNumber);
        sendCodes.setCode(sendedCode);
        sendCodes.setSendedDate(new Date());
        return sendCodes;
    }


}

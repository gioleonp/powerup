package com.pragma.twilio.dominio.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageModel {

    private String phoneNumber;
    private String message;
}

package com.pragma.twilio.dominio.api;

import com.pragma.twilio.dominio.model.MessageModel;

public interface IMessageServicePort {

    void sendMessage(MessageModel message);
}

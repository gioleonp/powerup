package com.pragma.twilio.dominio.spi;

import com.pragma.twilio.dominio.model.MessageModel;

public interface IMessageSendingPort {

    void sendMessage(MessageModel message);
}

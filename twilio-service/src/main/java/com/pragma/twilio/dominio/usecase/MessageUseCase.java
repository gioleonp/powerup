package com.pragma.twilio.dominio.usecase;

import com.pragma.twilio.dominio.api.IMessageServicePort;
import com.pragma.twilio.dominio.model.MessageModel;
import com.pragma.twilio.dominio.spi.IMessageSendingPort;

public class MessageUseCase implements IMessageServicePort {

    private final IMessageSendingPort messageSendingPort;

    public MessageUseCase(IMessageSendingPort message){
        this.messageSendingPort = message;
    }

    @Override
    public void sendMessage(MessageModel message) {
        messageSendingPort.sendMessage(message);
    }
}

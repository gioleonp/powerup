package com.pragma.twilio.application.handler;

import com.pragma.twilio.application.dto.MessageRequestDto;

public interface IMessageHandler {

    void sendMessage(MessageRequestDto messageRequestDto);
}

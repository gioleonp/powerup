package com.pragma.twilio.application.handler.impl;

import com.pragma.twilio.application.dto.MessageRequestDto;
import com.pragma.twilio.application.handler.IMessageHandler;
import com.pragma.twilio.application.mapper.IMessageRequestMapper;
import com.pragma.twilio.dominio.api.IMessageServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageHandlerImpl implements IMessageHandler {

    private final IMessageServicePort messageServicePort;
    private final IMessageRequestMapper messageRequestMapper;

    @Override
    public void sendMessage(MessageRequestDto messageRequestDto) {

        messageServicePort.sendMessage(messageRequestMapper.toModel(messageRequestDto));
    }
}

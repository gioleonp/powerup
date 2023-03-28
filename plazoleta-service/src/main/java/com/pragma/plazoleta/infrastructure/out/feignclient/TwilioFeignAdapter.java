package com.pragma.plazoleta.infrastructure.out.feignclient;

import com.pragma.plazoleta.domain.model.MessageModel;
import com.pragma.plazoleta.domain.spi.servicecommunication.ITwilioServiceCommunicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TwilioFeignAdapter implements ITwilioServiceCommunicationPort {

    private final IApiTwilioService apiTwilioService;

    @Override
    public void sendMessage(MessageModel messageModel) {
        apiTwilioService.sendMessage(messageModel);
    }
}

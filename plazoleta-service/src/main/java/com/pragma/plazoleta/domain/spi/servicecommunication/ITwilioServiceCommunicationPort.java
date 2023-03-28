package com.pragma.plazoleta.domain.spi.servicecommunication;

import com.pragma.plazoleta.domain.model.MessageModel;

public interface ITwilioServiceCommunicationPort {

    void sendMessage(MessageModel messageModel);
}

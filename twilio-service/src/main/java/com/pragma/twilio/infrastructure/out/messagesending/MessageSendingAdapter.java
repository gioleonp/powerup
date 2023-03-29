package com.pragma.twilio.infrastructure.out.messagesending;

import com.pragma.twilio.dominio.model.MessageModel;
import com.pragma.twilio.dominio.spi.IMessageSendingPort;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

public class MessageSendingAdapter implements IMessageSendingPort {

    public static final String ACCOUNT_SID = "ACc2271d3e3ca79a1aef3880903875dd39";
    public static final String AUTH_TOKEN = "609bcebcd0d03b610de56e128755aa27";

    @Override
    public void sendMessage(MessageModel messageModel) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message messageToSend =
                Message.creator(
                                new PhoneNumber(messageModel.getPhoneNumber()),
                                new PhoneNumber("+15077095761"),
                                messageModel.getMessage())
                        .create();
        System.out.println(messageToSend.getSid());
    }
}
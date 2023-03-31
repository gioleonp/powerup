package com.pragma.twilio.infrastructure.out.messagesending;

import com.pragma.twilio.dominio.model.MessageModel;
import com.pragma.twilio.dominio.spi.IMessageSendingPort;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MessageSendingAdapter implements IMessageSendingPort {

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    @Override
    public void sendMessage(MessageModel messageModel) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message messageToSend =
                Message.creator(
                                new PhoneNumber("+573058388527"),
                                new PhoneNumber("+15077095761"),
                                messageModel.getMessage())
                        .create();
        System.out.println(messageToSend.getSid());
    }
}

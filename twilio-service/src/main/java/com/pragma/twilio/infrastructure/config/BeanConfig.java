package com.pragma.twilio.infrastructure.config;

import com.pragma.twilio.dominio.api.IMessageServicePort;import com.pragma.twilio.dominio.spi.IMessageSendingPort;import com.pragma.twilio.dominio.usecase.MessageUseCase;import com.pragma.twilio.infrastructure.out.messagesending.MessageSendingAdapter;import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    @Bean
    public IMessageSendingPort messageSendingPort(){
        return new MessageSendingAdapter();
    }

    @Bean
    public IMessageServicePort messageServicePort(){
        return new MessageUseCase(messageSendingPort());
    }


}

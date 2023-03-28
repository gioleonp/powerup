package com.pragma.plazoleta.infrastructure.out.feignclient;

import com.pragma.plazoleta.domain.model.MessageModel;import org.springframework.cloud.openfeign.FeignClient;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "twilio-service", url = "http://localhost:8092/")
public interface IApiTwilioService {

    @PostMapping("/message/send")
    public void sendMessage(@RequestBody MessageModel messageModel);


}

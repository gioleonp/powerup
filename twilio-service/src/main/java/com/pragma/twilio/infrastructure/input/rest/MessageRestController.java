package com.pragma.twilio.infrastructure.input.rest;

import com.pragma.twilio.application.dto.MessageRequestDto;
import com.pragma.twilio.application.handler.IMessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageRestController {

    private final IMessageHandler messageHandler;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(
            @Valid @RequestBody MessageRequestDto messageRequestDto) {
        messageHandler.sendMessage(messageRequestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

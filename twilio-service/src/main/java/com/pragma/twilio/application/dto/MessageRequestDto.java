package com.pragma.twilio.application.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MessageRequestDto {

    @NotBlank(message = "PHONE NUMBER CAN'T BE BLANK")
    @Pattern(regexp = "\\+573\\d{9}", message = "PHONE NUMBER INVALID")
    private String phoneNumber;

    @NotBlank(message = "MESSAGE CAN'T BE BLANK") private String message;
}

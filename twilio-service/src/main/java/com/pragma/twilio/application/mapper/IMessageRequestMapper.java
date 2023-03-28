package com.pragma.twilio.application.mapper;

import com.pragma.twilio.application.dto.MessageRequestDto;import com.pragma.twilio.dominio.model.MessageModel;import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IMessageRequestMapper {

    MessageModel toModel(MessageRequestDto message);

}

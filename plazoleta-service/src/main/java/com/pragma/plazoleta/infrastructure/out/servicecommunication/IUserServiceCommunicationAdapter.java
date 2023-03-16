package com.pragma.plazoleta.infrastructure.out.servicecommunication;

import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service")
public interface IUserServiceCommunicationAdapter extends IUserServiceCommunicationPort {

}

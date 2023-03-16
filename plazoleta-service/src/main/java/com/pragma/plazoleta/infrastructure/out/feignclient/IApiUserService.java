package com.pragma.plazoleta.infrastructure.out.feignclient;

import com.pragma.plazoleta.application.dto.response.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8091/")
public interface IApiUserService {

    @GetMapping("api/v1/user/{id}")
    public UserResponseDto findUserById(@PathVariable("id") Long id);

}

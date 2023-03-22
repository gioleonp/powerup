package com.pragma.plazoleta.infrastructure.out.feignclient;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.dto.response.UserResponseDto;
import com.pragma.plazoleta.application.dto.response.UserResponseWithPasswordDto;
import org.apache.catalina.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8091/")
public interface IApiUserService {

    @GetMapping("api/v1/user/id/{id}")
    public UserResponseDto findUserById(@PathVariable("id") Long id);
    @GetMapping("api/v1/user/email/{email}")
    public UserResponseDto findUserByEmail(@PathVariable("email") String email);
    @GetMapping("api/v1/user/full/{email}")
    public UserResponseWithPasswordDto findFullUserByEmail(@PathVariable("email") String email);

    @PostMapping("api/v1/admin/proprietary")
    public void createProprietary(@RequestBody UserRequestDto userRequestDto);

    @PostMapping("api/v1/proprietary/employee")
    public void createEmployee(@RequestBody UserRequestDto userRequestDto);

    @PostMapping("api/v1/client")
    public void createClient(@RequestBody UserRequestDto userRequestDto);

    @GetMapping("api/v1/admin")
    public List<UserResponseDto> getAllUsers();

}

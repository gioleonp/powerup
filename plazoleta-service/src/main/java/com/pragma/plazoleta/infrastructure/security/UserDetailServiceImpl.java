package com.pragma.plazoleta.infrastructure.security;

import com.pragma.plazoleta.application.mapper.IUserResponseMapper;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final IUserServiceCommunicationPort userServiceCommunicationPort;
    private final IUserResponseMapper userResponseMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userResponseDto = userServiceCommunicationPort.findFullUserByEmail(username);
        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority(userResponseDto.getRol().getNombre()));
        return new UserDetailsImpl(userResponseDto, authorities);
    }
}

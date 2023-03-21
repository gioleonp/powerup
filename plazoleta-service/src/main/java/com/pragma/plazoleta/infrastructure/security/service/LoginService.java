package com.pragma.plazoleta.infrastructure.security.service;


import com.pragma.plazoleta.infrastructure.security.dto.AuthCredentialsDto;
import com.pragma.plazoleta.infrastructure.security.UserDetailsImpl;
import com.pragma.plazoleta.infrastructure.security.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserDetailsService userDetailsService;
    private final TokenUtils tokenUtils;

    public String login(AuthCredentialsDto authCredentials){

        UserDetailsImpl userDetail = (UserDetailsImpl)
                userDetailsService.loadUserByUsername(authCredentials.getEmail());

        List<GrantedAuthority> authorityList = (List<GrantedAuthority>) userDetail.getAuthorities();

        String token = tokenUtils.createToken(  userDetail);

        return token;
    }


}

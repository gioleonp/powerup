package com.pragma.userservice.infrastructure.security;

import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.model.UserModel;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private IUserServicePort servicePort;

    public UserDetailServiceImpl(IUserServicePort servicePort){
        this.servicePort = servicePort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = servicePort.findUserByEmail(username);
        Collection<? extends SimpleGrantedAuthority> authorities =
                Arrays.asList(new SimpleGrantedAuthority(user.getRol().getName().toString()));

        return new UserDetailsImpl(user, authorities);
    }
}

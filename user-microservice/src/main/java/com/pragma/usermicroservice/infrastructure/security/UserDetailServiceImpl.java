package com.pragma.usermicroservice.infrastructure.security;

import com.pragma.usermicroservice.application.handler.IUserHandler;
import com.pragma.usermicroservice.domain.model.UserModel;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private IUserHandler userHandler;

    public UserDetailServiceImpl(IUserHandler userHandler){
        this.userHandler = userHandler;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel user = userHandler.findByEmail(username);
        Collection<? extends SimpleGrantedAuthority> authorities =
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName().toString()));

        return new UserDetailsImpl(user, authorities);
    }
}

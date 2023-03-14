package com.pragma.usermicroservice.infrastructure.configuration;

import com.pragma.usermicroservice.domain.api.IRoleServicePort;
import com.pragma.usermicroservice.domain.api.IUserServicePort;
import com.pragma.usermicroservice.domain.spi.IUserPasswordEncoderPort;
import com.pragma.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.usermicroservice.domain.usecase.RoleUseCase;
import com.pragma.usermicroservice.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.pragma.usermicroservice.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.usermicroservice.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.pragma.usermicroservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.usermicroservice.infrastructure.out.jpa.repository.IRolRepository;
import com.pragma.usermicroservice.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.usermicroservice.domain.usecase.UserUseCase;
import com.pragma.usermicroservice.infrastructure.out.passwordencoder.BCryptPasswordEncoderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRolRepository objectRepository;
    private final IRolEntityMapper objectEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserPasswordEncoderPort userPasswordEncoderPort(){
        return new BCryptPasswordEncoderAdapter();
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), userPasswordEncoderPort());
    }

    @Bean
    public IRolePersistencePort rolPersistencePort() {
        return new RoleJpaAdapter(objectRepository, objectEntityMapper);
    }

    @Bean
    public IRoleServicePort rolServicePort() {
        return new RoleUseCase(rolPersistencePort());
    }
}
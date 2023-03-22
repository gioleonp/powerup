package com.pragma.userservice.infrastructure.configuration;

import com.pragma.userservice.domain.api.IRoleServicePort;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.spi.passwordencoding.IUserPasswordEncoderPort;
import com.pragma.userservice.domain.spi.persistence.IRolePersistencePort;
import com.pragma.userservice.domain.spi.persistence.IUserPersistencePort;
import com.pragma.userservice.domain.usecase.RoleUseCase;
import com.pragma.userservice.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.pragma.userservice.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.userservice.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.pragma.userservice.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.userservice.infrastructure.out.jpa.repository.IRolRepository;
import com.pragma.userservice.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.userservice.domain.usecase.UserUseCase;
import com.pragma.userservice.infrastructure.out.passwordencoder.BCryptPasswordEncoderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
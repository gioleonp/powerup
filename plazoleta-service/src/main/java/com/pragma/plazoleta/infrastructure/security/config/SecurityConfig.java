package com.pragma.plazoleta.infrastructure.security.config;

import com.pragma.plazoleta.infrastructure.security.UserDetailServiceImpl;
import com.pragma.plazoleta.infrastructure.security.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConfig {
    private UserDetailServiceImpl userDetailService;
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(UserDetailServiceImpl userDetailService,
                          JwtAuthorizationFilter jwtAuthorizationFilter){
        this.userDetailService = userDetailService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailService);

        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .authorizeRequests()
                .antMatchers("/swagger-ui/*", "/v3/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/api/v1/register").permitAll()
                .antMatchers("/api/v1/admin/**").hasRole("ADMINISTRADOR")
                .antMatchers("/api/v1/propietario").hasRole("ADMINISTRADOR")
                .antMatchers("/api/v1/user/**").hasRole("ADMINISTRADOR")
                .antMatchers("/api/v1/restaurante/paging").hasRole("CLIENTE")
                .antMatchers("/api/v1/plato/menu/**").hasRole("CLIENTE")
                .antMatchers("/api/v1/pedido/new").hasRole("CLIENTE")
                .antMatchers("/api/v1/pedido/cancel/**").hasRole("CLIENTE")
                .antMatchers("/api/v1/empleado/new").hasRole("PROPIETARIO")
                .antMatchers("/api/v1/categoria/**").hasRole("PROPIETARIO")
                .antMatchers("/api/v1/restaurante/**").hasRole("PROPIETARIO")
                .antMatchers("/api/v1/plato/**").hasRole("PROPIETARIO")
                .antMatchers("/api/v1/empleado/**").hasRole("EMPLEADO")
                .antMatchers("/api/v1/cliente/**").hasRole("CLIENTE")
                .antMatchers("/api/v1/pedido/**").hasRole("EMPLEADO")
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}

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
                .antMatchers("/admin/**").hasRole("ADMINISTRADOR")
                .antMatchers("/user/**").hasRole("ADMINISTRADOR")
                .antMatchers("/propietario/**").hasRole("PROPIETARIO")
                .antMatchers("/restaurante/**").hasRole("PROPIETARIO")
                .antMatchers("/platos/**").hasRole("PROPIETARIO")
                .antMatchers("/empleado/**").hasRole("EMPLEADO")
                .antMatchers("/cliente/**").hasRole("CLIENTE")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}

package com.example.Back.config;

import com.example.Back.jwt.JWTUtil;
import com.example.Back.oauth2.CustomSuccessHandler;
import com.example.Back.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Autowired
    private final CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private final CustomSuccessHandler customSuccessHandler;
    @Autowired
    private final JWTUtil jwtUtil;

    public SecurityConfiguration(CustomOAuth2UserService customOAuth2UserService,
                                 CustomSuccessHandler customSuccessHandler,
                                 JWTUtil jwtUtil){
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)

                .formLogin(AbstractHttpConfigurer::disable)

                .httpBasic(AbstractHttpConfigurer::disable)

                .headers((headerConfig)->{
                    headerConfig.frameOptions((HeadersConfigurer.FrameOptionsConfig::sameOrigin));
                })
                .oauth2Login(Customizer.withDefaults())

                .authorizeHttpRequests((auth)->{auth
                        .requestMatchers("/auth/test/**").authenticated()
                        .anyRequest().permitAll();
                })
//                로그인
                .oauth2Login((oauth2)->oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                                .successHandler(customSuccessHandler)
                        )
                .sessionManagement((session)->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .build();

    }
}

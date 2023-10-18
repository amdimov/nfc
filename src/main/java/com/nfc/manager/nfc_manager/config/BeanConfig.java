package com.nfc.manager.nfc_manager.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.UUID;

@Configuration
public class BeanConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return Pbkdf2PasswordEncoder
                .defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
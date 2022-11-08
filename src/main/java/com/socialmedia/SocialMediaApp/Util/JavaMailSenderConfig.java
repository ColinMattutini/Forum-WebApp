package com.socialmedia.SocialMediaApp.Util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class JavaMailSenderConfig {

    @Configuration
    public class Config {

        @Bean
        public JavaMailSender javaMailSender() {
            return new JavaMailSenderImpl();
        }

    }
}

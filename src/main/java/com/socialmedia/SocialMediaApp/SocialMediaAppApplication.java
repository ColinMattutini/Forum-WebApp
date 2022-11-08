package com.socialmedia.SocialMediaApp;

import com.socialmedia.SocialMediaApp.Model.Role;
import com.socialmedia.SocialMediaApp.Service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SocialMediaAppApplication {

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(SocialMediaAppApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(AppUserService appUserService){
//		return args -> {
//			appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
//		};
//	}

}

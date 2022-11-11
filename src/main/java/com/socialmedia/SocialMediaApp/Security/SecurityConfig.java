package com.socialmedia.SocialMediaApp.Security;

import com.socialmedia.SocialMediaApp.Util.CustomAuthenticationFilter;
import com.socialmedia.SocialMediaApp.Util.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationConfiguration configuration;

    @Bean
    @CrossOrigin(origins = "http://localhost:3000")
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.authorizeRequests().antMatchers("holder").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/api/user/{email}/post/{postId}/comment/newcomment", "/api/user/{email}/topic/{topicName}/post/savepost", "/api/user/{email}/topic/{topicName}/posts/{postId}", "/api/user/{email}/post/{postId}/review").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/api/user/signup", "/api/post/{postId}/comments", "/api/topic/{topicName}/posts", "/api/post/{postId}/negativeScore", "/api/post/{postId}/positiveScore", "/api/topic/newTopic", "api/user/confirm", "/api/topic/all/posts", "api/role/save").permitAll();
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(customAuthenticationFilter);
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Autowired
    void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://whimsical-centaur-0aeaf2.netlify.app"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Control-Type", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


}

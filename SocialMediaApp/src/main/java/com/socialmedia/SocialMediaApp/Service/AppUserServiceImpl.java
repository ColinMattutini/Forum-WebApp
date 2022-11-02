package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.EmailToken;
import com.socialmedia.SocialMediaApp.Model.Role;
import com.socialmedia.SocialMediaApp.Repo.AppUserRepo;
import com.socialmedia.SocialMediaApp.Repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private final RoleRepo roleRepo;
    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;

    private final EmailTokenServiceImpl emailTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByEmail(email);
        if(appUser == null || !appUser.isEnabled()){
            log.error("Email {} not found.", email);
            throw new UsernameNotFoundException("Email {} not found. Please check the username is correct.");
        }
        else{
            log.info("Email {} found successfully!", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(appUser.getEmail(), appUser.getPassword(), authorities);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
//        String email = appUser.getEmail();
        appUser.setUserCreatedDate(new Date(System.currentTimeMillis()));
        appUser.setEnabled(false);
        appUserRepo.save(appUser);
//        addRoleToAppUser(email, "ROLE_USER");
        String token = UUID.randomUUID().toString();
        EmailToken emailToken = new EmailToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30), appUser);
        emailTokenService.saveEmailToken(emailToken);
        emailTokenService.sendEmail(appUser ,token);
        return appUser;
    }

    @Override
    public AppUser getAppUserByEmail(String email) {
        return appUserRepo.findByEmail(email);
    }

    @Override
    public void saveRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void addRoleToAppUser(String email, String roleName) {
        AppUser appUser = appUserRepo.findByEmail(email);
        Role role = roleRepo.findByName(roleName);
        appUser.getRoles().add(role);
    }






}

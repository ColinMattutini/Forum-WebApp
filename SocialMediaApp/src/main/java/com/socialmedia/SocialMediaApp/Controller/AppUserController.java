package com.socialmedia.SocialMediaApp.Controller;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Role;
import com.socialmedia.SocialMediaApp.Repo.AppUserRepo;
import com.socialmedia.SocialMediaApp.Repo.RoleRepo;
import com.socialmedia.SocialMediaApp.Service.AppUserServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppUserController {

    private final AppUserServiceImpl appUserService;
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;

    @PostMapping("/user/signup")
    public ResponseEntity saveAppUser(@RequestBody AppUser appUser){
        AppUser emailEntry = appUserRepo.findByEmail(appUser.getEmail());
        AppUser usernameEntry = appUserRepo.findByUsername(appUser.getUsername());
        if(emailEntry != null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email already exists. Try a different email or try logging in.");
        }
        if(usernameEntry != null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username already exists. Please try a different one.");
        }
        else{
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
            return ResponseEntity.created(uri).body(appUserService.saveAppUser(appUser));
        }

    }

    @PostMapping("/role/save")
    public void saveRole(@RequestBody Role role){
        if(roleRepo.findByName(role.getName()) != null){
            throw new IllegalArgumentException("Role already exists in database!");
        } else{
            appUserService.saveRole(role);
        }
    }

    @PostMapping("/role/addroletouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        appUserService.addRoleToAppUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}


@Data
class RoleToUserForm{
    private String email;
    private String roleName;
}

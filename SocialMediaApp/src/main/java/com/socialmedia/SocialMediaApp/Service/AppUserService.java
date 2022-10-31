package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Role;
import org.springframework.stereotype.Service;


public interface AppUserService {

    AppUser saveAppUser(AppUser appUser);
    AppUser getAppUserByEmail(String email);
    void saveRole(Role role);
    void addRoleToAppUser(String email, String role);
}

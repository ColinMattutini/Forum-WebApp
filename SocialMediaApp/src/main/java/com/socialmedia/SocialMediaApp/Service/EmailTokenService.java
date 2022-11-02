package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.AppUser;

public interface EmailTokenService {

    String confirmToken(String token);
    String sendEmail(AppUser appUser, String emailToken);
    int enableAppUser(String email);

}

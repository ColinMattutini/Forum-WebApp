package com.socialmedia.SocialMediaApp.Repo;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);
    AppUser findByUsername(String username);
    //Optional<AppUser> findByEmail(String email);
}

package com.socialmedia.SocialMediaApp.Repo;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);
}

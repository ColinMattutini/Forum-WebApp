package com.socialmedia.SocialMediaApp.Repo;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);
    AppUser findByUsername(String username);
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
    //Optional<AppUser> findByEmail(String email);
}

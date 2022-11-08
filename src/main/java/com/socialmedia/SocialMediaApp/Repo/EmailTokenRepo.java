package com.socialmedia.SocialMediaApp.Repo;

import com.socialmedia.SocialMediaApp.Model.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional
public interface EmailTokenRepo extends JpaRepository<EmailToken, Long> {

    EmailToken findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE EmailToken e " +
            "SET e.confirmedAt = ?2 " +
            "WHERE e.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}

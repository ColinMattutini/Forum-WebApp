package com.socialmedia.SocialMediaApp.Repo;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostScoreRepo extends JpaRepository<PostScore, Long> {


    PostScore findByAppUser(AppUser appUser);
    List<PostScore> findByPost(Post post);

}

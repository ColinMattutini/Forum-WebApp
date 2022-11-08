package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostScore;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostScoreService {

    void saveScore(PostScore postScore, AppUser appUser, Post post);
    PostScore getUserPostScore(AppUser appUser);
    List<PostScore> getAllScoresByPost(Post post);
    int getTotalPositivePostScore(Post post);
    int getNegativePostScore(Post post);



}

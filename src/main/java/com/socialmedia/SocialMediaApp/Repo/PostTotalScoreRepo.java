package com.socialmedia.SocialMediaApp.Repo;

import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostTotalScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTotalScoreRepo extends JpaRepository<PostTotalScore, Long> {

    PostTotalScore findByPost(Post post);
}

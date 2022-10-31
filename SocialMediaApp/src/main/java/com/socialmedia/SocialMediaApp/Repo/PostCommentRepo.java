package com.socialmedia.SocialMediaApp.Repo;

import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepo extends JpaRepository<PostComment, Long> {

    List<PostComment> findByPost(Post post);



}

package com.socialmedia.SocialMediaApp.Service;

import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostComment;

import java.util.List;

public interface PostCommentService {

    PostComment savePostComment(PostComment postComment, AppUser appUser, Post post);
    void deletePostComment(PostComment postComment);
    List<PostComment> getAllPostComments(Post post);

}

package com.socialmedia.SocialMediaApp.Service;


import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.Topic;

import java.util.List;

public interface PostService {

    Post savePost(Post post, String topicName, String email);
    void deletePost(Post post);
    Post editPost(Post post);
    Post getPost(Long postId);
    List<Post> getAllPosts();
    List<Post> getAllPostsByTopic(Topic topic);
}

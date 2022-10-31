package com.socialmedia.SocialMediaApp.Service;


import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.Topic;
import com.socialmedia.SocialMediaApp.Repo.PostRepo;
import com.socialmedia.SocialMediaApp.Repo.TopicRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepo postRepo;
    private final TopicRepo topicRepo;
    private final AppUserServiceImpl appUserService;

    @Override
    public Post savePost(Post post, String topicName, String email) {
        Topic topic = topicRepo.findByTopicName(topicName);
        AppUser appUser = appUserService.getAppUserByEmail(email);
        post.setAppUser(appUser);
        post.setTopic(topic);
        post.setPostCreationDate(new Date(System.currentTimeMillis()));
        return postRepo.save(post);
    }

    @Override
    public void deletePost(Post post) {

    }

    @Override
    public Post editPost(Post post) {
        return null;
    }

    @Override
    public Post getPost(Long postId) {
        return postRepo.findByPostId(postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    @Override
    public List<Post> getAllPostsByTopic(Topic topic) {
        List<Post> posts = new ArrayList<>();
        postRepo.findByTopic(topic).forEach(posts::add);
        return posts;
    }
}

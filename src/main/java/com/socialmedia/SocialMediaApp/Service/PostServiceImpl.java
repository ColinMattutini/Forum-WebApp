package com.socialmedia.SocialMediaApp.Service;


import com.socialmedia.SocialMediaApp.Model.AppUser;
import com.socialmedia.SocialMediaApp.Model.Post;
import com.socialmedia.SocialMediaApp.Model.PostTotalScore;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepo postRepo;
    private final TopicRepo topicRepo;
    private final AppUserServiceImpl appUserService;
    private final PostTotalScoreServiceImpl postTotalScoreService;

    @Override
    public Post savePost(Post post, String topicName, String email) {
        Topic topic = topicRepo.findByTopicName(topicName);
        AppUser appUser = appUserService.getAppUserByEmail(email);
        post.setAppUser(appUser);
        post.setTopic(topic);
        post.setPostCreationDate(new Date(System.currentTimeMillis()));
        PostTotalScore postTotalScore = postTotalScoreService.savePostTotalScore(post);
        post.setPostTotalScore(postTotalScore);
        postRepo.save(post);

        return post;
    }

    @Override
    public void deletePost(Post post) {
        Long postId = post.getPostId();
        Optional<Post> oldPost = Optional.ofNullable(getPost(postId));
        if(oldPost.isPresent()){
            Post tempPost = oldPost.get();
            tempPost.setPostDescription("DELETED");
            tempPost.setPostName("DELETED");
            tempPost.setPostDeletionDate(new Date(System.currentTimeMillis()));
            postRepo.save(tempPost);
        }

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
        List<Post> posts = postRepo.findAll().stream().filter(e -> e.getPostDeletionDate() == null).collect(Collectors.toList());
        return posts;
    }

    @Override
    public List<Post> getAllPostsByTopic(Topic topic) {
        List<Post> posts = postRepo.findByTopic(topic).stream().filter(e -> e.getPostDeletionDate() == null).collect(Collectors.toList());
        return posts;
    }
}
